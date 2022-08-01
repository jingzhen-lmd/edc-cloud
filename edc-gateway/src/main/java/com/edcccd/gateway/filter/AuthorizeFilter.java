package com.edcccd.gateway.filter;

import cn.hutool.json.JSONObject;
import com.edcccd.gateway.MyRedisUtil;
import com.edcccd.gateway.MyTokenUtil;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;

@Order(-1)
@Component
public class AuthorizeFilter implements GlobalFilter {
    private static final String TOKEN = "token";

    @Resource
    MyTokenUtil tokenUtil;
    @Resource
    MyRedisUtil redisUtil;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        // 获取请求参数
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        // 获取token值
        String token = request.getHeaders().getFirst(TOKEN);

        if (token == null) {
            return out(response);
        }

        // 拿到token，通过token校验用户
        String userId = tokenUtil.getUserIdFromToken(token);
        if (userId == null) {
            return out(response);
        }
        // 拿redis中的数据
        String userJson = redisUtil.getString("login:user:"+userId);
        System.out.println(userJson);

        return chain.filter(exchange);
    }

    private Mono<Void> out(ServerHttpResponse response) {
        JSONObject message = new JSONObject();
        message.putOpt("success", false);
        message.putOpt("code", 20001);
        message.putOpt("data", "鉴权失败");
        byte[] bits = message.toString().getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = response.bufferFactory().wrap(bits);
        //response.setStatusCode(HttpStatus.UNAUTHORIZED);
        //指定编码，否则在浏览器中会中文乱码
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        return response.writeWith(Mono.just(buffer));
    }
}
