package com.edcccd.account.service.controller;

import com.edcccd.account.api.feign.CaptchaFeign;
import com.edcccd.account.service.service.serviceImpl.CaptchaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 验证码服务
 */
@RestController
@RequestMapping("captcha")
public class CaptchaController implements CaptchaFeign {


    @Autowired
    CaptchaServiceImpl captchaService;

    @GetMapping
    @Override
    public Boolean getCaptcha(@RequestParam("path") String path, @RequestParam("key") String key) {
        return captchaService.getCaptcha(path, key);
    }

    @GetMapping("verify")
    @Override
    public Boolean verifyCaptcha(@RequestParam("code") String code, @RequestParam("key") String key) {
        return captchaService.verifyCaptcha(code, key);
    }
}
