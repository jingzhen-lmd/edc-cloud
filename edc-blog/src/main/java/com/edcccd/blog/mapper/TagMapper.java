package com.edcccd.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.edcccd.blog.entity.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TagMapper extends BaseMapper<Tag> {

  @Select("select t.* from article_tag " +
      "left join tag t on article_tag.tag_id = t.id " +
      "where article_id = #{articleId}")
  List<Tag> queryByArticle(Long articleId);
}