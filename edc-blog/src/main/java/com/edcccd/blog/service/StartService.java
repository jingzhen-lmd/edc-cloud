package com.edcccd.blog.service;

import com.edcccd.blog.config.Model;

/**
 * 点赞服务
 */
public interface StartService {

    /**
     * 点赞，文章赞+1，点赞者集合添加文章id
     *
     * @param prefix 前缀，一般为模块名
     * @param id     文章、说说的id
     */
    void start(String prefix, String id);

    /**
     * 查询文章、说说、评论等点赞量
     *
     * @param model 模块
     * @param id    主键
     * @return 点赞数量
     */
    Integer likeCount(Model model, Long id);
}
