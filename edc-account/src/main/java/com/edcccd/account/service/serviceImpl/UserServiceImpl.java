package com.edcccd.account.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.edcccd.account.entity.User;
import com.edcccd.account.mapper.UserMapper;
import com.edcccd.account.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}

