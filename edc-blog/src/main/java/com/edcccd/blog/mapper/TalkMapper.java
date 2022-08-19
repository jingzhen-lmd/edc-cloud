package com.edcccd.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.edcccd.blog.entity.Talk;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TalkMapper extends BaseMapper<Talk> {
}