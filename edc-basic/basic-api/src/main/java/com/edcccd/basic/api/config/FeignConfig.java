package com.edcccd.basic.api.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class FeignConfig implements RequestInterceptor {
  @Override
  public void apply(RequestTemplate template) {
    ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    HttpServletRequest request = attributes.getRequest();
    //添加token
    template.header("token", request.getHeader("token"));
    // todo 远程调用时，考虑直接将用户信息放入请求头，
  }
}