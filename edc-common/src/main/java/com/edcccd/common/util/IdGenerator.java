package com.edcccd.common.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * 全局id 使用redis,雪花算法生成id
 */
@Component
public class IdGenerator {
  /**
   * 开始时间戳，2022.1.1
   */
  private static final long START_TIME = 1640995200L;

  @Autowired
  StringRedisTemplate template;

  public long generateId(String prefix) {
//    1、生成时间戳
    long now = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
    long time = now - START_TIME;

//    2、生成序列号
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy:MM:dd");
    String date = LocalDate.now().format(formatter);
    long increment = template.opsForValue().increment(date + ":" + prefix);

//    3、拼接合并，返回
    return time << 32 | increment;
  }
}
