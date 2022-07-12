package com.edcccd.demand.service;

import com.edcccd.basic.api.pojo.User;

import java.util.List;

/**
 * 点赞服务
 */
public interface FansService {
    boolean isLike(String user, String id);

    boolean like(String user, String id);

    List<User> listFans(String id);
}
