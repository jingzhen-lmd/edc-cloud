package com.edcccd.account.api.feign;

import com.edcccd.common.util.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 远程调用的校验接口
 */
@FeignClient("account-service")
public interface CheckClient {

  @GetMapping("check")
  Result<Void> checkToken(@RequestParam("token") String token);

}
