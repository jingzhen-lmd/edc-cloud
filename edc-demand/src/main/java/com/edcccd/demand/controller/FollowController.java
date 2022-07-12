package com.edcccd.demand.controller;

import com.edcccd.demand.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 关注功能
 */
@RestController
@RequestMapping("follow")
public class FollowController {

    @Autowired
    FollowService service;

    /**
     * 查询是否关注,user是否关注id
     */
    @GetMapping("{user}")
    public boolean isFollow(@PathVariable("user") String user, @RequestParam("id") String id) {
        return service.isFollow(user, id);
    }

    /**
     * 关注、取关,user关注/取关id
     */
    @PostMapping("{user}")
    public boolean follow(@PathVariable("user") String user, @RequestParam("id") String id) {
        return service.follow(user, id);
    }

    /**
     * 获取所有被关注列表（查我的关注）
     */
    @GetMapping("ns/{user}")
    public List<String> myNvShen(@PathVariable("user") String user) {
        return service.myNvShen(user);
    }

    /**
     * 获取所有关注列表（查我的天狗）
     */
    @GetMapping("me/{user}")
    public boolean myTianGou(@PathVariable("user") String user) {
        return false;
    }

    /**
     * 获取共同关注列表
     */
    @GetMapping("common/{user}")
    public List<String> commonFriend(@PathVariable("user") String user, @RequestParam("id") String id) {
        return service.commonFriend(user, id);
    }


}
