package com.edcccd.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.edcccd.blog.entity.Enjoy;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EnjoyMapper extends BaseMapper<Enjoy> {

    // void cancelLike(Long userId, Model model, Long id);
}