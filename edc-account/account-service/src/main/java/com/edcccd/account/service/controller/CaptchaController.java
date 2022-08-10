package com.edcccd.account.service.controller;

import com.edcccd.account.api.feign.CaptchaFeign;
import com.edcccd.account.service.service.serviceImpl.CaptchaServiceImpl;
import com.edcccd.common.util.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 验证码服务
 */
@RestController
@RequestMapping("captcha")
public class CaptchaController implements CaptchaFeign {

    @Resource
    CaptchaServiceImpl captchaService;

    @GetMapping
    @Override
    public Result<String> getCaptcha(@RequestParam("key") String key) {
        return captchaService.getCaptcha(key);
    }

    @GetMapping("verify")
    @Override
    public Result<Boolean> verifyCaptcha(@RequestParam("code") String code, @RequestParam("key") String key) {
        return captchaService.verifyCaptcha(code, key);
    }

    @GetMapping("phone")
    public Result<String> getCaptchaByPhone(@RequestParam("phone") String phone){
        return captchaService.getCaptchaByPhone(phone);
    }
}
