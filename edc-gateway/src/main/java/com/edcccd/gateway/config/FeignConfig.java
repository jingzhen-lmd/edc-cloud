package com.edcccd.gateway.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;

import java.util.stream.Collectors;

//import javax.servlet.http.HttpServletRequest;

@Configuration
public class FeignConfig implements RequestInterceptor {
  @Override
  public void apply(RequestTemplate template) {
//    ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//    HttpServletRequest request = attributes.getRequest();
//    //添加token
//    template.header("token", request.getHeader("token"));
//    // todo 远程调用时，考虑直接将用户信息放入请求头，
  }

  //    @Bean
//    Logger.Level feignLoggerLevel() {
//      return Logger.Level.FULL;
//    }


  @Bean
  @ConditionalOnMissingBean
  public HttpMessageConverters messageConverters(ObjectProvider<HttpMessageConverter<?>> converters) {
    return new HttpMessageConverters(converters.orderedStream().collect(Collectors.toList()));
  }

}

