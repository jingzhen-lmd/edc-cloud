package com.edcccd.gateway.config;

import cn.hutool.core.util.StrUtil;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

/**
 * 登录前校验token的filter，
 */
public class CheckTokenFilter implements WebFilter {

    // @Resource
    // FeignService feignService;

    // 请求头中获取token
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest();

        String token = request.getHeaders().getFirst("token");
        if (StrUtil.isBlank(token)) {
            // 校验token的有效性，保存在holder中？或放在请求头中？或放在redis中？
            // filterChain.doFilter(request, response);
            return chain.filter(exchange);
        }

        // 校验用户信息
        // Boolean isSuccess = feignService.saveUserInfo(token);

        return chain.filter(exchange);
    }

    // public Mono<Authentication> convert(ServerWebExchange serverWebExchange) {
    //     ServerHttpRequest request = serverWebExchange.getRequest();
    //     MediaType mediaType = request.getHeaders().getContentType();
    //     if (MediaType.APPLICATION_JSON.isCompatibleWith(mediaType)) {
    //         ObjectMapper mapper = new ObjectMapper();
    //         HttpMessageReader<Object> messageReader = new DecoderHttpMessageReader<>(new Jackson2JsonDecoder(mapper));
    //         ServerRequest serverRequest = ServerRequest
    //                 .create(serverWebExchange, List.of(messageReader));
    //         return serverRequest.bodyToMono(LoginModel.class).map(m -> {
    //             return new ImageCodeAuthenticationToken(m.getUsername(), m.getPassword(), m.getCode(), m.getUuid());
    //         });
    //     } else {
    //         return serverWebExchange.getFormData().map(data -> createAuthentication(data));
    //     }
    // }
}
