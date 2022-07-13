package com.edcccd.demand.controller;

import com.edcccd.demand.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 活跃统计功能 日期计算，从2022,7,1起
 */
@RestController
@RequestMapping("active")
public class ActiveController {

  @Autowired
  FollowService service;

  /**
   * web站点活跃度查询
   */
  @GetMapping("{user}")
  public boolean isFollow(@PathVariable("user") String user, @RequestParam("id") String id) {
    return service.isFollow(user, id);
  }

  /**
   * 用户登入，活跃度+1
   */
  @GetMapping("ns/{user}")
  public List<String> myNvShen(@PathVariable("user") String user) {
    return service.myNvShen(user);
  }

  /**
   * 用户登出，活跃度-1
   */
  @GetMapping("me/{user}")
  public boolean myTianGou(@PathVariable("user") String user) {
    return false;
  }

  /**
   * 个人总登录次数查询
   */
  @GetMapping("common/{user}")
  public List<String> commonFriend(@PathVariable("user") String user, @RequestParam("id") String id) {
    return service.commonFriend(user, id);
  }


}
