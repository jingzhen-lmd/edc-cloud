package com.edcccd.account.service.controller;

import com.edcccd.account.api.entity.User;
import com.edcccd.account.service.service.UserService;
import com.edcccd.common.util.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TestController {

    @Resource
    UserService userService;

    /**
     * 测试
     */
    @GetMapping("hello2")
    public Result<String> hello2() {
        return Result.success("nihaowa2222");
    }

    /**
     * 数据库测试
     */
    @GetMapping("hello1")
    public Result<User> hello1(String id) {
        User byId = userService.getById(id);
        return Result.success(byId);
    }








}
