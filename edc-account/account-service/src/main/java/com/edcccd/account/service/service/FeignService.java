package com.edcccd.account.service.service;

/**
 * 远程调用服务
 */
public interface FeignService {

  /**
   * 将用户信息保存在holder中
   */
  void saveUserInfo(String userJson);
}
