package com.edcccd.blog.service;

import com.edcccd.blog.util.Model;

/**
 * 点赞服务
 * 用户点赞数据量大，单人修改频率低，要求安全性高，由消息队列执行，最终存放在数据库中
 * 文章的点赞修改频率高，放在redis中即可。
 *
 */
public interface LikeService {

    /**
     * 点赞，文章赞+1，点赞者集合添加文章id
     *
     * @param model 前缀，一般为模块名
     * @param id     文章、说说的id
     * @return 点赞数量
     */
    Long like(Model model, Long id);

    /**
     * 查询文章、说说、评论等点赞量
     *
     * @param model 模块
     * @param id    主键
     * @return 点赞数量
     */
    Long likeCount(Model model, Long id);
}
