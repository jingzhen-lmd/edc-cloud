package com.edcccd.gateway.filter;

import com.edcccd.account.api.feign.CheckClient;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

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
    /*
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
    */
    // 鉴权成功，直接访问，feign不会被拦截
    return chain.filter(exchange);
  }
}
