package com.edcccd.common.service;

/**
 * 点赞服务
 */
public interface FansService {
    boolean isLike(String id);

    boolean like(String id);
}
