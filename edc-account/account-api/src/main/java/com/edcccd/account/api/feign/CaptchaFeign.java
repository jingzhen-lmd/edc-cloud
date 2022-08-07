package com.edcccd.account.api.feign;

import com.edcccd.common.util.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 远程调用的验证码服务
 */
@FeignClient("captcha")
public interface CaptchaFeign {

    /**
     * 获取验证码
     *
     * @param key 验证码id
     * @return 验证码base64格式
     */
    @GetMapping
    Result<String> getCaptcha(@RequestParam("key") String key);


    /**
     * 校验验证码
     *
     * @param code 答案
     * @param key  验证码id
     */
    @GetMapping("verify")
    Result<Boolean> verifyCaptcha(@RequestParam("code") String code, @RequestParam("key") String key);

}
