package com.edcccd.account.service.controller;

import com.edcccd.account.service.service.FeignService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 远程调用接口，供其他模块调用
 */
@RestController
public class FeignController {

  @Resource
  FeignService feignService;

  /**
   * 用于远程调用，校验token
   *
   * @param token
   *     token
   * @return 是否校验成功
   */
  @GetMapping("check")
  public Boolean checkToken(@RequestParam("token") String token) {
    return feignService.saveUserInfo(token);
  }

}
