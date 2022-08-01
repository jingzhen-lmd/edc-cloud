package com.edcccd.account.common.interceptor;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.edcccd.account.entity.User;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

import static com.edcccd.account.common.Constain.LOGIN_TOKEN;

/**
 * 登录拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {

    // 这个类是自己管理的，不能加注解
    StringRedisTemplate template;

    public LoginInterceptor(StringRedisTemplate template) {
        this.template = template;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取token
        String token = request.getHeader("Authorization");
        if (StrUtil.isBlank(token)) {
            response.setStatus(401);
            return false;
        }
        if (token.startsWith("Bearer")) {
            token = token.substring(7);
        }

        // 从redis中取出数据
        String user2Json = template.opsForValue().get(LOGIN_TOKEN + token);

//        校验，存到localthread中
        if (StrUtil.isBlank(user2Json)) {
            response.setStatus(401);
            return false;
        }
        User user = JSONUtil.toBean(user2Json, User.class);
        // UserHolder.saveUser(user);

        // 有则放行，刷新有效期
        template.opsForValue().set(LOGIN_TOKEN + token, user2Json, 70, TimeUnit.MINUTES);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // UserHolder.removeUser();
    }
}
