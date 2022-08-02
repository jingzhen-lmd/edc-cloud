package com.edcccd.account.service.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.edcccd.account.api.entity.User;
import com.edcccd.account.service.mapper.UserMapper;
import com.edcccd.account.service.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}

