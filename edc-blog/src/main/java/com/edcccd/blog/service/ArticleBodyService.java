package com.edcccd.blog.service;

import com.edcccd.blog.entity.ArticleBody;

public interface ArticleBodyService {


    /**
     * 通过文章的id查询
     */
    ArticleBody getByArticleId(Long articleId);

    /**
     * 通过文章的id删除
     */
    void removeByArticleId(Long articleId);
}
