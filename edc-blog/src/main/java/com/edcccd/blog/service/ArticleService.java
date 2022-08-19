package com.edcccd.blog.service;

import com.edcccd.blog.dto.DtArticle;
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
     * 分页查询
     */
    List<Article> list();

    void save(DtArticle article);

    /**
     * 删除文章
     *
     * @param articleId 文章id
     */
    void removeById(Long articleId);

    void updateById(DtArticle article);
}

