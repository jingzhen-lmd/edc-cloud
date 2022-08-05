package com.edcccd.gateway.config;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.stream.Collectors;

@Configuration
public class FeignConfig {
  // 日志配置
  //    @Bean
//    Logger.Level feignLoggerLevel() {
//      return Logger.Level.FULL;
//    }

  /**
   * 注册消息转化bean
   * <p>
   * 用于网关发起feign调用时，返回值的转化。 gateway的pom文件是webflux包，不支持web包
   * </p>
   */
  @Bean
  @ConditionalOnMissingBean
  public HttpMessageConverters messageConverters(ObjectProvider<HttpMessageConverter<?>> converters) {
    return new HttpMessageConverters(converters.orderedStream().collect(Collectors.toList()));
  }

  @Bean
  @LoadBalanced
  public RestTemplate restTemplate(){
    return new RestTemplate();
  }
}

