package com.edcccd.account.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.edcccd.account.api.entity.User;
import com.edcccd.common.util.Result;

public interface UserService extends IService<User> {

    /**
     * 注册用户
     *
     * @param user 用户
     */
    Result<String> register(User user);

    /**
     * 校验用户名是否存在
     *
     * @param userName 用户名
     * @return 是否存在
     */
    boolean isExist(String userName);
}

