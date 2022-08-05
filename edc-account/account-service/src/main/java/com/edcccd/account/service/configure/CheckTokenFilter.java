package com.edcccd.account.service.configure;

import cn.hutool.core.util.StrUtil;
import com.edcccd.account.service.service.FeignService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录前校验token的filter，
 */
@Component
public class CheckTokenFilter extends OncePerRequestFilter {

  @Resource
  FeignService feignService;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    String token = request.getHeader("token");
    if (StrUtil.isBlank(token)) {
      filterChain.doFilter(request, response);
      return;
    }

    Boolean isSuccess = feignService.saveUserInfo(token);

    // 放行
    filterChain.doFilter(request, response);
  }
}
