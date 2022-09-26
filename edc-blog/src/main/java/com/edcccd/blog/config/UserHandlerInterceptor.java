package com.edcccd.blog.config;

import cn.hutool.json.JSONUtil;
import com.edcccd.blog.entity.UserDetail;
import com.edcccd.blog.util.UserThreadLocal;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.edcccd.common.util.Const.USERINFO;


/**
 * 用户拦截器
 * 从请求中直接拿到用户数据，放到holder中备用
 */
@Configuration
public class UserHandlerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        try {
            String userJson = request.getHeader(USERINFO);
            UserDetail userDetail = JSONUtil.toBean(userJson, UserDetail.class);
            UserThreadLocal.setUser(userDetail);
        } catch (Exception ignored) {
        }
        return true;
    }

    /**
     * 删除
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                Exception ex) {
        UserThreadLocal.remove();
    }
}