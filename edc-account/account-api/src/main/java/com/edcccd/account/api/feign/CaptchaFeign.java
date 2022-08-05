package com.edcccd.account.api.feign;

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
     * @param path 保存路径，文件夹地址即可。
     * @param key  验证码id
     * @return 是否成功
     */
    @GetMapping
    Boolean getCaptcha(@RequestParam("path") String path, @RequestParam("key") String key);


    /**
     * 校验验证码
     *
     * @param code 答案
     * @param key  验证码id
     */
    @GetMapping("verify")
    Boolean verifyCaptcha(@RequestParam("code") String code, @RequestParam("key") String key);

}
