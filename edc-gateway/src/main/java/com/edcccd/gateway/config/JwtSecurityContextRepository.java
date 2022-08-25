package com.edcccd.gateway.config;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.edcccd.common.util.RedisUtil;
import com.edcccd.gateway.entity.UserDetail;
import com.edcccd.gateway.util.MyTokenUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import static com.edcccd.account.api.common.Constain.LOGIN_USER;

/**
 * 获取请求头中带过来的token值，解析并验证用户信息
 */
public class JwtSecurityContextRepository implements ServerSecurityContextRepository {

    private MyTokenUtil tokenUtil = new MyTokenUtil();
    private RedisUtil redisUtil;

    public JwtSecurityContextRepository(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

    /**
     * 认证成功后保存用户信息
     */
    @Override
    public Mono<Void> save(ServerWebExchange exchange, SecurityContext context) {
        return Mono.empty();
    }

    @Override
    public Mono<SecurityContext> load(ServerWebExchange exchange) {
        String path = exchange.getRequest().getPath().toString();
        if ("/login".equals(path)) {
            return Mono.empty();
        }
        String token = exchange.getRequest().getHeaders().getFirst("token");
        if (StrUtil.isBlank(token)) {
            return Mono.empty();
        }

        // token能正常解析，表示token有效并对应数据库已知用户
        String userId = tokenUtil.getUserIdFromToken(token);
        if (StrUtil.isBlank(userId)) {
            throw new RuntimeException("token非法" + token);
        }

        String userJson = redisUtil.getString(LOGIN_USER + userId);
        if (StrUtil.isBlank(userJson)) {
            throw new RuntimeException("用户失效" + token);
        }
        UserDetail userDetail = JSONUtil.toBean(userJson, UserDetail.class);
        if (userDetail == null) {
            throw new RuntimeException("用户json转换失败" + token);
        }

        Authentication newAuthentication = new UsernamePasswordAuthenticationToken(userDetail, null, userDetail
                .getAuthorities());

        // 放入holder中
        SecurityContextHolder.getContext().setAuthentication(newAuthentication);

        // return new ReactiveAuthenticationManager() {
        //     @Override
        //     public Mono<Authentication> authenticate(Authentication authentication) {
        //         // 如果对token有足够的安全认可，可以采用无状态凭证策略，将username和authorities放置在token串中解析获取，此处就可以不用查询数据库验证
        //         Authentication auth = new UsernamePasswordAuthenticationToken(userDetail, null, userDetail
        //                 .getAuthorities());
        //         return Mono.just(auth);
        //     }
        // }.authenticate(newAuthentication).map(SecurityContextImpl::new);

        // SecurityContext securityContext = new SecurityContextImpl(newAuthentication);
        return Mono.empty();
    }
}