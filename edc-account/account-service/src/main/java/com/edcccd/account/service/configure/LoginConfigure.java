package com.edcccd.account.service.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

/**
 * 登录配置
 */
@EnableWebSecurity //此注解用于import HttpSecurity包
public class LoginConfigure {

    @Resource
    CheckTokenFilter tokenFilter;

    // 自定义filterChain
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // 前后端分离，关闭csrf验证
        http.csrf()
            .disable()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS); // 永远不会获取session

        // 增加filter
        http.addFilterAfter(tokenFilter, UsernamePasswordAuthenticationFilter.class);

        // 配置权限
        http.authorizeRequests()
            .antMatchers("/hello").anonymous()
            .antMatchers("/hello2").hasAuthority("visit:delete")
            .antMatchers("/login").permitAll()
            .anyRequest().authenticated();

        return http.build();
    }


    @Resource
    private AuthenticationConfiguration authenticationConfiguration;

    /**
     * 初始化authenticationManager
     */
    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * 注入加密方式，替换原来的数据库查询加密
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
