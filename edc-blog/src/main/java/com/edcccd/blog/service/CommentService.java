package com.edcccd.blog.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.edcccd.blog.dto.DtComment;
import com.edcccd.blog.entity.Comment;

import java.util.List;

public interface CommentService extends IService<Comment> {

    /**
     * 获取对象的所有评论
     *
     * @param id 被评论对象
     */
    List<DtComment> getByTargetId(Long id);

    /**
     * 分页查询评论
     *
     */
    PageDTO<Comment> page(String type);

}
