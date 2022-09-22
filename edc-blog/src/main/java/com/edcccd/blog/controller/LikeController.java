/*
 * @ 版权所有(C)，上海海鼎信息工程股份有限公司，2021，所有权利保留。
 * @ 项目名：	edc-cloud
 * @ 文件名：
 * @ 模块说明：
 * @ 修改历史：
 * @ 2022/9/5- jingzhen - 创建。
 */
package com.edcccd.blog.controller;

import com.edcccd.blog.service.LikeService;
import com.edcccd.blog.util.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 点赞功能
 */
@RestController
@Controller
@RequestMapping("like")
public class LikeController {

    @Resource
    LikeService service;

    /**
     * 点赞
     *
     * @param model 模块
     * @param id    主键
     * @return 点赞数量
     */
    @GetMapping
    public Long like(Model model, Long id) {
        return service.like(model, id);
    }

    /**
     * 查询文章、说说、评论等点赞量
     *
     * @param model 模块
     * @param id    主键
     * @return 点赞数量
     */
    Long likeCount(Model model, Long id) {
        return service.like(model, id);
    }
}
