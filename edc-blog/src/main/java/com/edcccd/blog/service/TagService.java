package com.edcccd.blog.service;

import com.edcccd.blog.entity.Tag;

import java.util.List;

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

    List<Tag> listAll();

    Long count();
}
