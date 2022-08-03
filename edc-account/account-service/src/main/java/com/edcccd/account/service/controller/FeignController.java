package com.edcccd.account.service.controller;

import com.edcccd.account.service.service.FeignService;
import com.edcccd.common.util.Result;
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

  @GetMapping("check")
  public Result<String> checkToken(@RequestParam("token") String token) {
    feignService.saveUserInfo(token);

    return Result.success("asda");
  }

}
