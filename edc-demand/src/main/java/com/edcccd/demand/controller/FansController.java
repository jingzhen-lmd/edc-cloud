package com.edcccd.demand.controller;

import com.edcccd.basic.api.pojo.User;
import com.edcccd.basic.api.pojo.UserDTO;
import com.edcccd.demand.pojo.Video;
import com.edcccd.demand.service.FansService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 作品点赞,使用sortSet实现
 * 这里用户是直接写在列表中的，理论上应该直接从session中拿
 *
 */
@RestController
@RequestMapping("Fans")
public class FansController {

    @Autowired
    FansService service;

    /**
     * 查询是否点赞
     */
    @GetMapping("like/{user}")
    public boolean isLike(@PathVariable("user") String user, @RequestParam("id") String id) {
        return service.isLike(user,id);
    }

    /**
     * 点赞,再点取消
     *
     * @param id videoId
     * @return 点赞状态
     */
    @PostMapping("like/{user}")
    public boolean like(@PathVariable("user") String user, @RequestParam("id") String id) {
        return service.like(user,id);
    }

    /**
     * 查询作品的所有粉丝
     */
    @GetMapping("fans")
    public List<User> listFans(@RequestParam("id") String id) {
        return service.listFans(id);
    }

    /**
     * 查询作品的前n位粉丝，按时间戳排序
     */
    @GetMapping("fans/order")
    public List<UserDTO> myLike(@RequestParam("id") String id, @RequestParam("num") int num) {
        return service.listFans(id,num);
    }


}
