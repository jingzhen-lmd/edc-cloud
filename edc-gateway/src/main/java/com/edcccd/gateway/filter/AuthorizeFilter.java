package com.edcccd.gateway.filter;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import com.edcccd.account.api.feign.CheckClient;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;

@Order(-1)
@Component
public class AuthorizeFilter implements WebFilter {
//  public class AuthorizeFilter implements GlobalFilter {
//  public class AuthorizeFilter implements GatewayFilter {
  private static final String TOKEN = "token";

  @Resource
  CheckClient myFeignClient;

// GlobalFilter全局过滤器，也会拦截feign的请求
// gateway过滤器，局部，可以成功,两种写法完全一样，合并一个方法
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        // 获取请求参数
//        ServerHttpRequest request = exchange.getRequest();
//        ServerHttpResponse response = exchange.getResponse();
//        // 获取请求头中token值
//        String token = request.getHeaders().getFirst(TOKEN);
//        if (StrUtil.isBlank(token)) {
//            return out(response);
//        }
//        // 鉴权，存redis
////      Boolean aBoolean = myFeignClient.checkToken(token);
//      Boolean aBoolean = myFeignClient.checkToken(System.currentTimeMillis() + "");
//        if (!aBoolean)
//          return out(response);
//
//      return chain.filter(exchange);
//    }

  // web过滤器，之过滤web请求，所以也生效
  @Override
  public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
    ServerHttpRequest request = exchange.getRequest();
    ServerHttpResponse response = exchange.getResponse();
    // 获取请求头中token值
    String token = request.getHeaders().getFirst(TOKEN);
    if (StrUtil.isBlank(token)) {
      return out(response);
    }

    // 鉴权，存redis
    Boolean aBoolean = myFeignClient.checkToken(token);
    if (!aBoolean) {
      return out(response);
    }

    // 鉴权成功，直接访问，feign不会被拦截
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
