package com.edcccd.gateway.config;

import cn.hutool.core.util.StrUtil;
import com.edcccd.gateway.entity.UserDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.RequestPath;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * 静态资源的filter，按权限路由到对应路径
 */
@Slf4j
public class CheckTokenFilter implements WebFilter {
    /**
     * 前端模块的key值
     */
    public static String MODULE = "module";
    /**
     * 分割符
     */
    public static String SEPARATOR = "/";

    /**
     * 静态资源的后缀
     */
    private final List<String> resources = Arrays.asList(".jpg", ".png");

    /**
     * 权限等级（大到小）,优先匹配大
     */
    private final List<String> authorities = Arrays.asList("vip2", "vip1");

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        // 用户信息
        UserDetail userDetail;
        // 请求路径
        RequestPath path;
        // 静态资源跳转模块
        String module;

        try {
            // 校验静态找资源
            path = exchange.getRequest().getPath();
            String fileName = path.subPath(path.elements().size() - 1).value();
            boolean isResources = resources.stream().anyMatch(fileName::endsWith);
            if (!isResources) {
                throw new Exception("不是静态资源");
            }

            // 校验模块为空，直接跳转
            module = exchange.getRequest().getQueryParams().get(MODULE).get(0);
            if (StrUtil.isBlank(module)) {
                throw new Exception("没有模块名，直接跳转");
            }

            try {
                userDetail = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                List<String> userPowers = userDetail.getPowers();

                //校验vip权限，优先匹配最高等级权限
                Optional<String> authority = authorities.stream().filter(userPowers::contains).findFirst();

                if (!authority.isPresent())
                    throw new Exception("没有权限");

                ServerWebExchange build = exchange.mutate()
                        //  重定向路径为===》/模块名/权限/删了static前缀的原路径
                        .request(builder -> builder.path(
                                SEPARATOR + module + SEPARATOR + authority.get() + path.subPath(2))).build();
                return chain.filter(build);
            } catch (Exception e) {
                log.warn(e.getMessage());
                // 没有权限跳转到普通页面
                ServerWebExchange build = exchange.mutate()
                        .request(builder -> builder.path(
                                SEPARATOR + module + path.subPath(2).value())).build();
                return chain.filter(build);
            }
        } catch (Exception e) {
            log.warn(e.getMessage());
            return chain.filter(exchange);
        }
    }
}
