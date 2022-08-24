package com.edcccd.gateway;

import com.edcccd.account.api.feign.CheckClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.config.EnableWebFlux;
import reactor.core.publisher.Mono;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@EnableDiscoveryClient
@SpringBootApplication
@RestController
@EnableWebFlux
@EnableFeignClients(clients = CheckClient.class)
public class SpringGatewayApplication {
  public static void main(String[] args) {
    SpringApplication.run(SpringGatewayApplication.class, args);
  }

  /**
   * 不限制用户访问（需登录）
   * @return
   */
  @RequestMapping("/getUser")
  @ResponseBody
  public Mono<String> getUser(){
    return Mono.just("getUser");
  }

  /**
   * 普通用户访问
   * post
   * @return
   */
  @PreAuthorize("hasRole('USER')")
  @RequestMapping(value = "/user", method = {RequestMethod.GET,RequestMethod.POST})
  @ResponseBody
  public Mono<String> user(Principal principal){
    System.out.println(principal.getName());
    return Mono.just("hello "+principal.getName());
  }

  /**
   * 超管用户访问
   * @return
   */
  //    @PreAuthorize("hasPermission('ROLE_ADMIN')")
  //    @Secured("ROLE_ADMIN")
  @PreAuthorize("hasRole('ADMIN')")
  @RequestMapping(value = "/admin", method = {RequestMethod.GET,RequestMethod.POST})
  @ResponseBody
  public Mono<String> admin(Principal principal, Authentication authentication){
    System.out.println(authentication.getAuthorities());
    System.out.println(principal.getName());
    return Mono.just("admin "+principal.getName());
  }

  @RequestMapping(value = "/login", method = {RequestMethod.GET,RequestMethod.POST})
  @ResponseBody
  public Mono<Object> login(ServerHttpResponse response){
    Map<String, String> responseMap = new HashMap<>();
    responseMap.put("code", "failure");
    responseMap.put("msg", "您还未登录");
    response.setStatusCode(HttpStatus.UNAUTHORIZED);
    return Mono.just(responseMap);
  }
}
