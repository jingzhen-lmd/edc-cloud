package com.edcccd.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.edcccd.blog.dto.DtCategory;
import com.edcccd.blog.entity.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

    List<DtCategory> articleCount();

    @Select("select c.name from article_category a left join category c on c.id = a.category_id where a" +
            ".article_id=#{articleId}")
    String getByArticleId(Long articleId);
}