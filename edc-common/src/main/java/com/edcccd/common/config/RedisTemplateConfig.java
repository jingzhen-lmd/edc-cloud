package com.edcccd.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisTemplateConfig {

  @Bean
  public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
    //为了开发方便，一般直接使用<String,Object>
    RedisTemplate<String, Object> template = new RedisTemplate<>();
    //设置连接工厂，默认的
    template.setConnectionFactory(redisConnectionFactory);

//    json序列化方式
    GenericJackson2JsonRedisSerializer jsonRedisSerializer = new GenericJackson2JsonRedisSerializer();

    //String的序列化
    StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
    //key采用String的序列化方式
    template.setKeySerializer(stringRedisSerializer);
    //hash的key也采用String的序列化方式
    template.setHashKeySerializer(stringRedisSerializer);
    //value采用json的方式序列化
    template.setValueSerializer(jsonRedisSerializer);
    //hash的value采用json的方式序列化
    template.setHashValueSerializer(jsonRedisSerializer);
    //将所有的配置放进去
    template.afterPropertiesSet();
    return template;
  }
}
