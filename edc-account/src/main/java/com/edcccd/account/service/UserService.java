package com.edcccd.account.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.edcccd.account.common.Result;
import com.edcccd.account.pojo.User;

import javax.servlet.http.HttpServletRequest;

public interface UserService extends IService<User> {
    Result<String> login(HttpServletRequest request, User user);

    Result<String> getCheckCode(String phone);

    Result<String> phoneLogin(User user);
}
