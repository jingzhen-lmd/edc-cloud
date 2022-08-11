package com.edcccd.account.service.util;

import com.edcccd.common.util.RedisUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class MyRedisUtil {

    @Resource
    StringRedisTemplate stringRedisTemplate;
    @Resource
    RedisTemplate<String, Object> redisTemplate;

    @Bean
    public RedisUtil redisUtil() {
        return new RedisUtil(stringRedisTemplate, redisTemplate);
    }
}
