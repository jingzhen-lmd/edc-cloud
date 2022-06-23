package com.edcccd.common;

import com.edcccd.common.util.IdGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

@SpringBootTest
public class CommonTest {

  @Autowired
  StringRedisTemplate template;

  @Autowired
  IdGenerator wholeID;

  @Test
  public void test1() {
    template.opsForValue().set("chushi", "初始化", 40, TimeUnit.SECONDS);
    System.out.println(template.opsForValue().get("chushi"));
  }

  @Test
  public void test2() {
    for (int i = 0; i < 100; i++) {
      long order = wholeID.generateId("order");
    }
  }
}
