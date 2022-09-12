package com.edcccd.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.edcccd.blog.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper extends BaseMapper<Comment> {
}