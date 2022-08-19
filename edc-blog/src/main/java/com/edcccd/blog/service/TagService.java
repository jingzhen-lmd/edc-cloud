package com.edcccd.blog.service;

/**
 * 标签服务
 */
public interface TagService {


    /**
     * 通过商品id删除,对应对应标签关系(不删标签)
     *
     * @param articleId 商品
     */
    void removeByArticleId(Long articleId);
}
