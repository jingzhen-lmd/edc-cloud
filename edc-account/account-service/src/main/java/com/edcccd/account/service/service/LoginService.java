package com.edcccd.account.service.service;

import com.edcccd.account.api.entity.User;
import com.edcccd.common.util.Result;

public interface LoginService {

    Result<String> login(User user);

    /**
     * 验证码登录
     *
     * @param captcha 输入的验证码
     * @param user    默认使用用户的手机号作为key值
     */
    Result<Boolean> loginCaptcha(String captcha, User user);
}

