package com.edcccd.blog.config;

import com.edcccd.common.util.RedisUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class RedisConfig {

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Bean
    public RedisUtil redisUtil(RedisTemplate<String, Object> redisTemplate) {
        return new RedisUtil(stringRedisTemplate, redisTemplate);
    }

    /**
     * 修改序列化方式
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        //为了开发方便，一般直接使用<String,Object>
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        //设置连接工厂，默认的
        template.setConnectionFactory(redisConnectionFactory);

        //String的序列化
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        //key采用String的序列化方式
        template.setKeySerializer(stringRedisSerializer);
        //hash的key也采用String的序列化方式
        template.setHashKeySerializer(stringRedisSerializer);
        //    json序列化方式
        GenericJackson2JsonRedisSerializer jsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
        //value采用json的方式序列化
        template.setValueSerializer(jsonRedisSerializer);
        //hash的value采用json的方式序列化
        template.setHashValueSerializer(jsonRedisSerializer);
        //将所有的配置放进去
        template.afterPropertiesSet();
        return template;
    }
}
