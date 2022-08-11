package com.edcccd.account.service.service.serviceImpl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.edcccd.account.service.entity.UserDetail;
import com.edcccd.account.service.service.FeignService;
import com.edcccd.account.service.util.MyTokenUtil;
import com.edcccd.common.util.RedisUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.edcccd.account.api.common.Constain.LOGIN_USER;

@Service
public class FeignServiceImpl implements FeignService {

    @Resource
    MyTokenUtil tokenUtil;
    @Resource
    RedisUtil redisUtil;

    @Override
    public Boolean saveUserInfo(String token) {
        System.out.println("开始校验token" + token);
        String userId = tokenUtil.getUserIdFromToken(token);
        if (StrUtil.isBlank(userId)) {
            throw new RuntimeException("token非法" + token);
        }

        String userJson = redisUtil.getString(LOGIN_USER + userId);
        if (StrUtil.isBlank(userJson)) {
            throw new RuntimeException("用户失效" + token);
        }

        UserDetail userDetail = JSONUtil.toBean(userJson, UserDetail.class);
        if (userDetail == null) {
            throw new RuntimeException("用户json转换失败" + token);
        }
        // 放入holder中
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDetail, null, userDetail.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        System.out.println("完成校验token");
        return true;
    }
}
