package com.edcccd.common.util;

import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * redis缓存
 */
@Component
public class RedisCache {

  @Autowired
  private StringRedisTemplate template;
  @Autowired
  private RedisTemplate<String, Object> redisTemplate;

  /**
   * 字符串缓存
   */
  public boolean addCache(String pre, String value) {
    if (StrUtil.isBlank(pre))
      return false;

    Boolean isSuccess = template.opsForValue().setIfAbsent(pre, value, 30, TimeUnit.MINUTES);

    if (isSuccess == null)
      return false;
    return isSuccess;
  }

  /**
   * 通过反射将value的key、value保存到redis
   */
  public void addMap(String pre, Object value) {
    if (StrUtil.isBlank(pre) || value == null)
      return;

    Map<String, Object> redisDataMap = new HashMap<>();

    Arrays.stream(value.getClass().getDeclaredFields())
        .forEach(field -> {
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
   * 删除缓存
   */
  public boolean removeCache(String cacheName) {
    if (StrUtil.isBlank(cacheName))
      return false;

    Boolean isSuccess = template.delete(cacheName);

    if (isSuccess == null)
      return false;
    return isSuccess;
  }


}
