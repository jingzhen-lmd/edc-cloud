package com.edcccd.account.controller;

import com.edcccd.account.entity.User;
import com.edcccd.account.service.LoginService;
import com.edcccd.common.util.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
     * 登录
     */
    @PostMapping("login")
    public Result<String> login(@RequestBody User user) {
        return loginService.login(user);
    }

    /**
     * 登出
     */
    @PostMapping("logout")
    public Result<Void> logout() {

        return Result.success();
    }
}
