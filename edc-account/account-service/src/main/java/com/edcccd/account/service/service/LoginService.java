package com.edcccd.account.service.service;

import com.edcccd.account.api.entity.User;
import com.edcccd.common.util.Result;

public interface LoginService {

    Result<String> login(User user);
}

