package com.edcccd.blog.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.edcccd.blog.dto.DtComment;
import com.edcccd.blog.entity.Comment;
import com.edcccd.blog.util.Model;

import java.util.List;

public interface CommentService extends IService<Comment> {

    /**
     * 获取对象的所有评论
     * @param model 模块
     * @param id 被评论对象
     */
    List<DtComment> getByTargetId(Model model, Long id);

    /**
     * 分页查询评论
     *
     */
    PageDTO<Comment> page(String type);

    /**
     * 添加评论
     *
     * @param comment 评论
     */
    void saveComment(Comment comment);

    /**
     * 点赞评论
     *
     * @param commentId 评论id
     */
    void saveCommentLike(Integer commentId);

}
