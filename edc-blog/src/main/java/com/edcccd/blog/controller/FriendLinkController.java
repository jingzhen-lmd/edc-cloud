package com.edcccd.blog.controller;

import com.edcccd.blog.entity.FriendLink;
import com.edcccd.blog.service.FriendLinkService;
import com.edcccd.common.util.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 友情链接
 */
@RestController
@RequestMapping("friendLink")
public class FriendLinkController {
    /**
     * 服务对象
     */
    @Resource
    private FriendLinkService service;

    /**
     * 查询所有友链
     */
    @GetMapping
    public Result<List<FriendLink>> listAll() {
        List<FriendLink> list = service.list(null);
        return Result.success(list);
    }

}
