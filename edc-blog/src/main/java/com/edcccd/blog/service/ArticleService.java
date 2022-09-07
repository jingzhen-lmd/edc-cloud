package com.edcccd.blog.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.edcccd.blog.dto.DtArticle;
import com.edcccd.blog.dto.DtArticleSmall;
import com.edcccd.blog.entity.Article;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ArticleService {

    /**
     * 通过id查需详细信息
     *
     * @param id id
     */
    DtArticle getById(Long id);

    /**
     * 通过fenlei
     *
     * @param categoryId fenlei
     */
    PageDTO<DtArticle> getByCategory(Long categoryId);

    /**
     * 通过fenlei
     *
     * @param tagId fenlei
     */
    PageDTO<DtArticle> getByTag(Long tagId);

    /**
     * 分页查询
     */
    List<Article> list();

    // 首页的分页查询
    PageDTO<DtArticle> page();

    void save(DtArticle article);

    /**
     * 删除文章
     *
     * @param articleId 文章id
     */
    void removeById(Long articleId);

    void updateById(DtArticle article);

    // 文章数
    Long count();

    // 查询最新的轻量级文章
    List<DtArticleSmall> Latest(int num);


}

