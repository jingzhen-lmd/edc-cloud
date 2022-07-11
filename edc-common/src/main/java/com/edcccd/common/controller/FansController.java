package com.edcccd.common.controller;

import com.edcccd.common.pojo.User;
import com.edcccd.common.pojo.Video;
import com.edcccd.common.service.FansService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 作品点赞
 */
@RestController
@RequestMapping("Fans")
public class FansController {

    @Autowired
    FansService service;

    /**
     * 查询是否点赞
     */
    @GetMapping("like")
    public boolean isLike(@RequestParam("id") String id) {

        return service.isLike(id);
    }

    /**
     * 点赞,再点取消
     *
     * @param id videoId
     * @return 点赞状态
     */
    @PostMapping("like")
    public boolean like(@RequestParam("id") String id) {

        return service.like(id);
    }

    /**
     * 查询作品粉丝
     */
    @GetMapping("fans")
    public List<User> listFans(@RequestParam("id") String id) {

        return null;
    }

    /**
     * 查询我的喜欢
     */
    @GetMapping("myLike")
    public List<Video> myLike(@RequestParam("id") String id) {

        return null;
    }


}
