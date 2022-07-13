package com.edcccd.demand.controller;

import com.edcccd.demand.service.ActiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 活跃统计功能 日期计算，从2022,7,1起
 */
@RestController
@RequestMapping("active")
public class ActiveController {

    @Autowired
    ActiveService service;

    /**
     * web站点活跃度查询
     */
    @GetMapping
    public long webActive() {
        return service.webActive();
    }

    /**
     * 用户登入，活跃度+1
     */
    @GetMapping("login/{user}")
    public void loginActive(@PathVariable("user") String user) {
        service.loginActive(user);
    }

    /**
     * 用户登出，活跃度-1
     */
    @GetMapping("logout/{user}")
    public void removeActive(@PathVariable("user") String user) {
        service.logoutActive(user);
    }

    /**
     * 个人总登录次数查询
     */
    @GetMapping("count/{user}")
    public long userActiveCount(@PathVariable("user") String user) {
        return service.userActiveCount(user);
    }


}
