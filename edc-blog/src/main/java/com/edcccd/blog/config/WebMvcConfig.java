package com.edcccd.blog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * web mvc配置
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    // todo ip地址限流

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 分页拦截器
        registry.addInterceptor(new PageHandlerInterceptor());
        // 用户信息拦截器
        // registry.addInterceptor(new UserHandlerInterceptor());
    }
}
