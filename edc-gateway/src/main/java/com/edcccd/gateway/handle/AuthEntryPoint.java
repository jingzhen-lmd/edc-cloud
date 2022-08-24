package com.edcccd.gateway.handle;

import com.edcccd.common.util.Result;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.authentication.HttpBasicServerAuthenticationEntryPoint;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import static com.edcccd.gateway.config.LoginConfigure.writeWith;

/**
 * 用来解决匿名用户访问无权限资源时的异常
 */
public class AuthEntryPoint extends HttpBasicServerAuthenticationEntryPoint {
    @Override
    public Mono<Void> commence(ServerWebExchange serverWebExchange, AuthenticationException e) {
        return writeWith(serverWebExchange, Result.fail(401,"匿名用户访问无权限资源时的异常"));
    }
}

