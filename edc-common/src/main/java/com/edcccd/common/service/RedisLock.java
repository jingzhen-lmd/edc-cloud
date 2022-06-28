package com.edcccd.common.service;

public interface RedisLock {

  /**
   * 获取锁
   *
   * @return 是否获取
   */
  boolean getLock(String prefix);

  /**
   * 释放锁
   *
   * @return 是否成功
   */
  boolean unlock(String prefix);
}
