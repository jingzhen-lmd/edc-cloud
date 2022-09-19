package com.edcccd.account.api.feign;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 远程调用的校验接口
 */
// @FeignClient("account-service")
public interface CheckClient {

    @GetMapping("/account/check")
    Boolean checkToken(@RequestParam("token") String token);

}
