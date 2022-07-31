package com.edcccd.account.service.serviceImpl;

import cn.hutool.json.JSONUtil;
import com.edcccd.account.common.MyRedisUtil;
import com.edcccd.account.common.MyTokenUtil;
import com.edcccd.account.entity.User;
import com.edcccd.account.entity.UserDetail;
import com.edcccd.account.service.LoginService;
import com.edcccd.common.util.Result;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.edcccd.account.common.Constain.LOGIN_USER;

@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    AuthenticationManager manager;
    @Resource
    MyRedisUtil redisUtil;
    @Resource
    MyTokenUtil tokenUtil;

    @Override
    public Result<String> login(User user) {
        // 1、调用模块的认证授权方法，在security模块中登录，
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user
                .getUserName(), user.getPassword());

        // 2、进行认证，登陆错误由过滤器处理
        Authentication authenticate = manager.authenticate(authenticationToken);

        UserDetail userDetail = (UserDetail) authenticate.getPrincipal();
        // 3、登录成功，使用redis记录用户数据
        redisUtil.addCache(LOGIN_USER + userDetail.getUser().getId(), JSONUtil.toJsonStr(userDetail));

        // 3、将用户id打包为token，返回
        String token = tokenUtil.generateToken(userDetail.getUser().getId());

        return Result.success(token);
    }
}

