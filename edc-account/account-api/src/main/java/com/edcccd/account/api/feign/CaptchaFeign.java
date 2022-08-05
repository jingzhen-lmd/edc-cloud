package com.edcccd.account.api.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 远程调用的验证码服务
 */
@FeignClient("captcha")
public interface CaptchaFeign {

  @GetMapping
  Boolean getCaptcha(@RequestBody String key);


  /**
   * 校验验证码
   *
   * @param code
   *     答案
   * @param key
   *     验证码id
   */
  @GetMapping("verify")
  Boolean verifyCaptcha(@RequestParam("code") String code, @RequestBody String key);

}
