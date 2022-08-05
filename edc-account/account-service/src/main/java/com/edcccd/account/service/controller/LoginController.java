package com.edcccd.account.service.controller;

import com.edcccd.account.api.entity.User;
import com.edcccd.account.service.service.LoginService;
import com.edcccd.common.util.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 登录接口
 */
@RestController
public class LoginController {

    @Resource
    LoginService loginService;

    /**
     * 测试
     */
    @GetMapping("hello")
    public Result<String> hello() {
        return Result.success("nihaowa");
    }

    /**
     * 测试
     */
    @GetMapping("hello2")
    public Result<String> hello2() {
        return Result.success("nihaowa2222");
    }

    /**
     * 账号密码登录
     */
    @PostMapping("login")
    public Result<String> login(@RequestBody User user) {
        return loginService.login(user);
    }

    /**
     * 验证码登录
     */
    @PostMapping("loginCaptcha")
    public Result<Boolean> loginCaptcha(@RequestParam("captcha") String captcha, @RequestBody User user) {
        return loginService.loginCaptcha(captcha, user);
    }

    /**
     * 登出
     */
    @PostMapping("logout")
    public Result<Void> logout() {

        return Result.success();
    }

    /**
     * 注册用户
     */
    @PostMapping("register")
    public Result<String> register(@RequestBody User user) {
        return loginService.login(user);
    }

}
