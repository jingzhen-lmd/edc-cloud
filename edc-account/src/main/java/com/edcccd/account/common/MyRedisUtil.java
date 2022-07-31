package com.edcccd.account.common;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.edcccd.common.util.RedisData;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * redis缓存工具类
 */
@Component
public class MyRedisUtil {

    @Resource
    private StringRedisTemplate template;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 字符串缓存,默认30分钟过期
     */
    public  boolean addCache(String pre, String value) {
        if (StrUtil.isBlank(pre))
            return false;
        Boolean isSuccess = template.opsForValue().setIfAbsent(pre, value, 30, TimeUnit.MINUTES);

        return isSuccess != null && isSuccess;
    }

    /**
     * 字符串缓存,自定义时间
     *
     * @param pre      前缀
     * @param value    data
     * @param time     time
     * @param timeUnit timeUnit
     * @return isSuccess
     */
    public boolean addCache(String pre, String value, long time, TimeUnit timeUnit) {
        if (StrUtil.isBlank(pre))
            return false;
        Boolean isSuccess = template.opsForValue().setIfAbsent(pre, value, time, timeUnit);
        return isSuccess != null && isSuccess;
    }

    /**
     * 对象缓存(toJson),默认30分钟过期
     */
    public <T> void addCache(String pre, T value) {
        assert pre != null;
        assert value != null;
        String jsonStr = JSONUtil.toJsonStr(value);
        template.opsForValue().set(pre, jsonStr, 30, TimeUnit.MINUTES);
    }

    /**
     * 对象缓存(toJson),自定义时间
     */
    public <T> void addCache(String pre, T value, long time, TimeUnit timeUnit) {
        assert pre != null;
        assert value != null;
        String jsonStr = JSONUtil.toJsonStr(value);
        template.opsForValue().set(pre, jsonStr, time, timeUnit);
    }

    /**
     * 对象缓存,逻辑过期时间缓存,用于解决缓存击穿问题
     */
    public <T> void addCacheLogic(String pre, T object, long time, TimeUnit timeUnit) {
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime date = localDateTime.plusSeconds(timeUnit.toSeconds(time));

        RedisData<T> data = new RedisData<>(date, object);
        String toJson = JSONUtil.toJsonStr(data);

        template.opsForValue().set(pre, toJson);
    }

    /**
     * 对象缓存为map
     */
    public void addMap(String pre, Object value) {
        if (StrUtil.isBlank(pre) || value == null)
            return;

        Map<String, Object> redisDataMap = new HashMap<>();

        Arrays.stream(value.getClass().getDeclaredFields()).forEach(field -> {
            field.setAccessible(true);
            Object o = "";
            try {
                o = field.get(value);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            redisDataMap.put(field.getName(), o);
        });

        redisTemplate.opsForHash().putAll(pre, redisDataMap);
    }

    /**
     * 最简单的直接查询字符串
     */
    public String getString(String key){
        return template.opsForValue().get(key);
    }

    /**
     * 查询对象,并反序列化为指定类,缓存空值来解决缓存穿透问题
     */
    public <R> R getByCashThrow(String key, Class<R> valueClass) {
        if (StrUtil.isBlank(key)) {
            return null;
        }
        String resultJson = template.opsForValue().get(key);

        if (StrUtil.isBlank(resultJson)) {
            template.opsForValue().set(key, "", 3, TimeUnit.SECONDS);
            return null;
        }

        return JSONUtil.toBean(resultJson, valueClass);
    }

    /**
     * 查询RedisData，逻辑过期,解决缓存击穿问题
     */
    public <R, ID> R getByCashLogic(String pre, ID id, Class<R> valueClass, Function<ID, R> sql) {
        assert StrUtil.isNotBlank(pre);
        assert valueClass != null;
        String key = pre + id;
        long time = 30;
        TimeUnit unit = TimeUnit.MINUTES;

        //        获取redis对象，查看是否过期
        String jsonData = template.opsForValue().get(key);
        if (StrUtil.isNotBlank(jsonData)) {
            //        内部变量只能是JSONObject，无法转换
            RedisData<JSONObject> result = JSONUtil.toBean(jsonData, RedisData.class);
            LocalDateTime dateTime = result.getDate();
            //            查询对象没有过期则直接返回
            if (dateTime.isAfter(LocalDateTime.now())) {
                return JSONUtil.toBean(result.getObject(), valueClass);
            }
        }
        //       查不到或者key过期了,需要通过数据库查一次
        R apply = sql.apply(id);

        //        查不到,缓存放数据,5秒
        if (apply == null) {
            template.opsForValue().setIfAbsent(key, "", 5, TimeUnit.SECONDS);
            return null;
        }
        //        查到了，写缓存,返回
        template.opsForValue().set(key, JSONUtil.toJsonStr(apply), time, unit);
        return apply;
    }

    /**
     * 删除缓存
     */
    public boolean removeCache(String key) {
        if (StrUtil.isBlank(key))
            return false;

        Boolean isSuccess = template.delete(key);

        if (isSuccess == null)
            return false;
        return isSuccess;
    }
}
