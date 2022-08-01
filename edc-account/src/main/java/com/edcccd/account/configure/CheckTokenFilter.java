package com.edcccd.account.configure;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.edcccd.account.common.MyRedisUtil;
import com.edcccd.account.common.MyTokenUtil;
import com.edcccd.account.entity.UserDetail;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.edcccd.account.common.Constain.LOGIN_USER;

/**
 * 登录前校验token的filter，
 */
@Component
public class CheckTokenFilter extends OncePerRequestFilter {

    @Resource
    MyTokenUtil tokenUtil;
    @Resource
    MyRedisUtil redisUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("token");
        if (StrUtil.isBlank(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        String userId = tokenUtil.getUserIdFromToken(token);
        if (StrUtil.isBlank(userId)) {
            // todo 日志记录取出token有问题
            System.out.println("取出token有问题" + token);
            return;
        }

        String userJson = redisUtil.getString(LOGIN_USER + userId);
        if (StrUtil.isBlank(userJson)) {
            // todo 日志记录取出userJson有问题
            System.out.println("找不到redis上的用户信息" + userJson);
            filterChain.doFilter(request, response);
            return;
        }

        UserDetail userDetail = JSONUtil.toBean(userJson, UserDetail.class);
        // 放入holder中
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetail
                .getUser(), null, userDetail.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        // 放行
        filterChain.doFilter(request, response);
    }
}
