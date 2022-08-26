package com.edcccd.gateway.handle;

import cn.hutool.json.JSONUtil;
import com.edcccd.common.util.RedisUtil;
import com.edcccd.common.util.Result;
import com.edcccd.gateway.entity.UserDetail;
import com.edcccd.gateway.util.MyTokenUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
import reactor.core.publisher.Mono;

import static com.edcccd.account.api.common.Constain.LOGIN_USER;
import static com.edcccd.gateway.config.LoginConfigure.writeWith;

/**
 * 验证成功后处理器
 */
public class AuthSuccessHandler implements ServerAuthenticationSuccessHandler {

    RedisUtil redisUtil;
    MyTokenUtil tokenUtil = new MyTokenUtil();

    public AuthSuccessHandler(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

    /**
     * 信息放入redis，返回token
     */
    @Override
    public Mono<Void> onAuthenticationSuccess(WebFilterExchange webFilterExchange, Authentication authentication) {
        UserDetail userDetail = (UserDetail) authentication.getPrincipal();

        String userId = userDetail.getUser().getId().toString();

        redisUtil.addCache(LOGIN_USER + userId, JSONUtil.toJsonStr(userDetail), 180, true);
        String token = tokenUtil.generateToken(userId);
        return writeWith(webFilterExchange.getExchange(), Result.success(token));
    }
}
