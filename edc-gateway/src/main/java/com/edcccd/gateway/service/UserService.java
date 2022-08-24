package com.edcccd.gateway.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.edcccd.account.api.entity.User;
import com.edcccd.gateway.entity.UserDetail;
import com.edcccd.gateway.mapper.UserMapper;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户认证与权限查询
 * <p>用于替换默认的内存中认证</p>
 */
@Component
public class UserService implements ReactiveUserDetailsService {
    @Resource
    UserMapper mapper;

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUserName, username);
        // 查询用户
        User user = mapper.selectOne(wrapper);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        //  查询权限
        List<String> powers = mapper.listUserPower(user.getId());
        UserDetail userDetail = new UserDetail();
        userDetail.setUser(user);
        userDetail.setPowers(powers);

        return Mono.just(userDetail);
    }
}
