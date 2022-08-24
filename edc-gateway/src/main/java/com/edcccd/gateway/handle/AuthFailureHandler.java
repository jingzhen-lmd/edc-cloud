package com.edcccd.gateway.handle;

import com.edcccd.common.util.Result;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationFailureHandler;
import reactor.core.publisher.Mono;

import static com.edcccd.gateway.config.LoginConfigure.writeWith;

/**
 * 登录验证失败处理器
 */
public class AuthFailureHandler implements ServerAuthenticationFailureHandler {

    @Override
    public Mono<Void> onAuthenticationFailure(WebFilterExchange webFilterExchange, AuthenticationException exception) {
        webFilterExchange.getExchange().getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);

        String message;
        if (exception instanceof UsernameNotFoundException) {
            message = "用户不存在 ";
        } else if (exception instanceof BadCredentialsException) {
            message = "密码错误 ";
        } else if (exception instanceof LockedException) {
            message = "用户锁定 ";
        } else if (exception instanceof AccountExpiredException) {
            message = "账户过期 ";
        } else if (exception instanceof DisabledException) {
            message = "账户不可用 ";
        } else {
            message = "系统错误 ";
        }
        return writeWith(webFilterExchange.getExchange(), Result.fail(402, message + exception.getMessage()));
    }
}
