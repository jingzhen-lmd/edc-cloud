package com.edcccd.blog;

import com.edcccd.blog.service.ArticleService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@SpringBootTest
public class MyTest {

    @Resource
    RedisTemplate<String, Object> redisTemplate;

    @Resource
    private ArticleService service;

    @Test
    public void test() {
        System.out.println(service.count());
    }

    @Test
    public void test2() {
        redisTemplate.expire("哇哈哈哈", 90, TimeUnit.HOURS);
    }
}
