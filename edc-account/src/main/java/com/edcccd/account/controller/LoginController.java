package com.edcccd.account.controller;

import com.edcccd.account.common.Result;
import com.edcccd.account.pojo.User;
import com.edcccd.account.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 登录模块
 */
@RequestMapping("account/login")
@RestController
public class LoginController {

    @Autowired
    private UserService service;

    /**
     * 登录
     *
     * @param user 用户
     */
    @PostMapping
    public Result<String> login(HttpServletRequest request, @RequestBody User user) {
        return service.login(request, user);
    }

    /**
     * 登出
     */
    @GetMapping("/loginOut")
    public Result<Void> loginOut(HttpServletRequest request) {
        request.removeAttribute("user");
        return Result.success();
    }

    /**
     * 获取短信验证码
     *
     * @param phone 手机号
     */
    @GetMapping("/getCheckCode")
    public Result<String> getCheckCode(@RequestParam("phone") String phone) {
        return service.getCheckCode(phone);
    }

    /**
     * 手机登录
     *
     * @param user 用户
     */
    @PostMapping("/phoneLogin")
    public Result<String> phoneLogin(@RequestBody User user) {
        return service.phoneLogin(user);
    }
}
