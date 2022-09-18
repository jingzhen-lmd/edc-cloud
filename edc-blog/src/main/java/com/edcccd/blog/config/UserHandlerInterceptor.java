package com.edcccd.blog.config;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.edcccd.blog.entity.User;
import com.edcccd.blog.util.UserThreadLocal;
import com.mysql.cj.util.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.edcccd.blog.util.Constant.USERINFO;

/**
 * 用户拦截器
 * 从请求中直接拿到用户数据，放到holder中备用
 */
@Configuration
public class UserHandlerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
      String userJson = request.getParameter(USERINFO);
      if (StringUtils.isNullOrEmpty(userJson)) {
        return true;
      }

      User user = JSONUtil.toBean(userJson, User.class);
      if (BeanUtil.isEmpty(user)) {
        return true;
      }

      UserThreadLocal.setUser(user);
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