package com.edcccd.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.edcccd.blog.dto.DtArticle;
import com.edcccd.blog.entity.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ArticleMapper extends BaseMapper<Article> {


    @Select("select a.*,ab.txt body from article a left join article_body ab on a.id =ab.article_id")
    IPage<DtArticle> page(IPage<DtArticle> page);
}