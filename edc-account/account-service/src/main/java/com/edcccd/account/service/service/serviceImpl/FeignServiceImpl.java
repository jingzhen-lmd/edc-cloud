package com.edcccd.account.service.service.serviceImpl;

import cn.hutool.json.JSONUtil;
import com.edcccd.account.service.entity.UserDetail;
import com.edcccd.account.service.service.FeignService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class FeignServiceImpl implements FeignService {
  @Override
  public void saveUserInfo(String userJson) {
    System.out.println("开始校验token"+userJson);

//    UserDetail userDetail = JSONUtil.toBean(userJson, UserDetail.class);
//    // 放入holder中
//    UsernamePasswordAuthenticationToken authenticationToken =
//        new UsernamePasswordAuthenticationToken(userDetail.getUser(), null, userDetail.getAuthorities());
//    SecurityContextHolder.getContext().setAuthentication(authenticationToken);

    System.out.println("完成校验token");
  }
}
