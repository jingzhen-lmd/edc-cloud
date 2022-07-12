package com.edcccd.demand.service;

import com.edcccd.basic.api.pojo.User;

import java.util.List;

/**
 * 点赞服务
 */
public interface FansService {
    /**
     * 查询是否点赞
     * @param user
     * @param id
     * @return
     */
    boolean isLike(String user, String id);

    /**
     * 点赞
     * @param user
     * @param id
     * @return
     */
    boolean like(String user, String id);

    /**
     * 查询作品的所有点赞用户
     * @param id
     * @return
     */
    List<User> listFans(String id);
}
