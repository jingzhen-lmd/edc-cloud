package com.edcccd.blog.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.edcccd.blog.dto.DtComment;
import com.edcccd.blog.entity.Comment;
import com.edcccd.blog.service.CommentService;
import com.edcccd.common.util.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 评论
 */
@Api(tags = "评论服务")
@RestController
@RequestMapping("comment")
public class CommentController {

    @Autowired
    CommentService service;


    /**
     * 查询对象的评论数量
     *
     * @param type     类型
     * @param targetId 对象id
     */
    @GetMapping("count")
    public Result<Long> count(String type, String targetId) {
        LambdaQueryWrapper<Comment> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Comment::getTargetId, targetId);
        wrapper.eq(Comment::getTargetType, type);
        wrapper.isNull(Comment::getPid);
        return Result.success(service.count(wrapper));
    }

    /**
     * 获取对象的所有评论
     *
     * @param targetId 对象id
     */
    @GetMapping("dtComments")
    public Result<List<DtComment>> getByTargetId(Long targetId) {
        List<DtComment> dtComments = service.getByTargetId(targetId);
        return Result.success(dtComments);
    }


}
