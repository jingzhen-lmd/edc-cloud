package com.edcccd.account.service.controller;

import com.edcccd.account.api.entity.User;
import com.edcccd.account.service.service.LoginService;
import com.edcccd.account.service.service.UserService;
import com.edcccd.common.util.Result;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 登录接口
 */
@Validated
@RestController
public class LoginController {

    @Resource
    LoginService loginService;

    @Resource
    UserService userService;

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
     * @param captcha 验证码,使用user的phone生成
     * @param user 用户
     * @return token
     */
    @PostMapping("loginCaptcha")
    public Result<String> loginCaptcha(@RequestParam("captcha") String captcha, @RequestBody User user) {
        return loginService.loginCaptcha(captcha, user);
    }

    /**
     * 登出
     */
    // todo 具体查看有哪些逻辑需要实现
    @PostMapping("logout")
    public Result<Void> logout() {

        return Result.success();
    }

    /**
     * 注册用户
     */
    @PostMapping("register")
    public Result<String> register(@RequestBody @Validated User user) {
        return userService.register(user);
    }

    /**
     * 校验昵称是否重复
     */
    @GetMapping("check-name")
    public Result<Boolean> checkName(@RequestParam("userName") String userName) {
        boolean isRepeat = userService.isExist(userName);
        return Result.success(isRepeat);
    }
}
