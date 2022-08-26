package com.edcccd.gateway.config;

import cn.hutool.json.JSONUtil;
import com.edcccd.common.util.RedisUtil;
import com.edcccd.common.util.Result;
import com.edcccd.gateway.handle.*;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;

/**
 * 登录配置
 */
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class LoginConfigure {

    private final String[] permitAll = {"/login", "/account/**", "/blog/**",
            "/loginCaptcha", "/register", "/captcha/**"};

    @Resource
    RedisUtil redisUtil;

    /**
     * 自定义filterChain
     */
    @Bean
    public SecurityWebFilterChain filterChain(ServerHttpSecurity http,
                                              ReactiveAuthenticationManager manager) {
        // 前后端分离，关闭csrf验证
        http.csrf().disable()
                .cors().disable()
                .httpBasic().disable();

        http.formLogin()// 配置允许表单验证
                .loginPage("/login") // 登录页面
                .authenticationFailureHandler(new AuthFailureHandler())  // 验证失败处理器
                .authenticationSuccessHandler(new AuthSuccessHandler(redisUtil)) // 验成功处理器
                .and().logout()
                .logoutSuccessHandler(new LogoutHandler()) // 登出成功后处理器
                .and().exceptionHandling()
                .authenticationEntryPoint(new AuthEntryPoint()) //用来解决匿名用户访问无权限资源时的异常
                .accessDeniedHandler(new AccessDeniedHandler()); // 用来解决认证过的用户访问无权限资源时的异常

        // 增加鉴权管理器
        http.authenticationManager(manager)
                // 配置上下文验证器(鉴权管理器)
                .securityContextRepository(new JwtSecurityContextRepository(redisUtil))
        .addFilterAt(new CheckTokenFilter(), SecurityWebFiltersOrder.HTTP_BASIC)// 增加自己的filter
        ;

        // 配置权限
        http.authorizeExchange()
                .pathMatchers("/hello2", "/vip/**").hasAuthority("visit:delete")
                .pathMatchers(permitAll).permitAll()
                .anyExchange().authenticated();

        return http.build();
    }

    /**
     * 提供用于获取UserDetail的Service
     */
    @Bean
    public ReactiveAuthenticationManager reactiveAuthenticationManager(
            ReactiveUserDetailsService userDetailsService, PasswordEncoder encoder) {
        UserDetailsRepositoryReactiveAuthenticationManager authenticationManager =
                new UserDetailsRepositoryReactiveAuthenticationManager(userDetailsService);
        authenticationManager.setPasswordEncoder(encoder);
        return authenticationManager;
    }

    /**
     * 输出响应信息
     */
    public static Mono<Void> writeWith(ServerWebExchange exchange, Result<?> responseMap) {
        ServerHttpResponse response = exchange.getResponse();
        String body = JSONUtil.toJsonPrettyStr(responseMap);
        DataBuffer buffer = response.bufferFactory().wrap(body.getBytes(StandardCharsets.UTF_8));
        //指定编码，否则在浏览器中会中文乱码?测试一下再加
        // response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        return response.writeWith(Mono.just(buffer));
    }

    /**
     * 注入加密方式，替换原来的数据库查询加密
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

