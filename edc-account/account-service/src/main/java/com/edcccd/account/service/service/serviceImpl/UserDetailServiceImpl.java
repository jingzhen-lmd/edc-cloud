package com.edcccd.account.service.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.edcccd.account.api.entity.User;
import com.edcccd.account.service.entity.UserDetail;
import com.edcccd.account.service.mapper.UserMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 通过用户名查需的具体方法
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Resource
    UserMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUserName, username);

        // 查询用户
        User user = mapper.selectOne(wrapper);
        //  查询权限
        List<String> powers = mapper.listUserPower(user.getId());

        UserDetail userDetail = new UserDetail(user, powers);

        return userDetail;
    }
}
