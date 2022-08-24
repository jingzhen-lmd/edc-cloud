package com.edcccd.gateway.handle;

import com.edcccd.common.util.Result;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import static com.edcccd.gateway.config.LoginConfigure.writeWith;

/**
 * 用来解决认证过的用户访问无权限资源时的异常
 */
public class AccessDeniedHandler implements ServerAccessDeniedHandler {
    @Override
    public Mono<Void> handle(ServerWebExchange serverWebExchange, AccessDeniedException e) {
        return writeWith(serverWebExchange, Result.fail(401, "用户没有足够的权限"));
    }
}

