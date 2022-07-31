package com.edcccd.account.service;

import com.edcccd.account.entity.User;
import com.edcccd.common.util.Result;

public interface LoginService {

    Result<String> login(User user);
}

