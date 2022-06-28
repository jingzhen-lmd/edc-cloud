package com.edcccd.common.service.serviceImpl;

import com.edcccd.common.service.RedisLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisLockImpl implements RedisLock {

  @Autowired
  StringRedisTemplate template;

  @Override
  public boolean getLock(String prefix) {
    Boolean lock = template.opsForValue().
        setIfAbsent(prefix, "lock", 100, TimeUnit.SECONDS);
    return lock;
  }

  @Override
  public boolean unlock(String prefix) {

    Boolean isDelete = template.delete(prefix);
    return isDelete;
  }
}
