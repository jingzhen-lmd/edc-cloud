package com.edcccd.gateway.handle;

import com.edcccd.common.util.Result;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.logout.ServerLogoutSuccessHandler;
import reactor.core.publisher.Mono;

import static com.edcccd.gateway.config.LoginConfigure.writeWith;

/**
 * 退出登录处理器
 */
public class LogoutHandler implements ServerLogoutSuccessHandler {
    @Override
    public Mono<Void> onLogoutSuccess(WebFilterExchange exchange, Authentication authentication) {
        return writeWith(exchange.getExchange(), Result.success("退出成功"));
    }
}
