package com.edcccd.account.service.service.serviceImpl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.edcccd.account.api.entity.User;
import com.edcccd.account.service.mapper.UserMapper;
import com.edcccd.account.service.service.UserService;
import com.edcccd.common.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    UserMapper mapper;

    @Override
    public Result<String> register(User user) {
        check(user.getUserName());
        check(user.getPassword());
        check(user.getPhone());

        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getPhone, user.getPhone());
        User saveUser = mapper.selectOne(wrapper);
        if (saveUser != null) {
            return Result.fail(500, "该号码已经注册！");
        }

        if (isExist(user.getUserName())) {
            return Result.fail(500, "该用户名已经注册！");
        }

        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        mapper.insert(user);

        return Result.success("注册成功");
    }

    @Override
    public boolean isExist(String userName) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUserName, userName);
        User user = mapper.selectOne(wrapper);
        return user != null;
    }


    private static void check(String data) {
        if (data == null || StrUtil.isBlank(data)) {
            throw new RuntimeException("输入参数为空：" + data);
        }
    }
}

