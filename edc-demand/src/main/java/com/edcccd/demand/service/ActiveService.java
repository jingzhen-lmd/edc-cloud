package com.edcccd.demand.service;

/**
 * 活跃度服务
 */
public interface ActiveService {
    long webActive();

    void loginActive(String user);

    void logoutActive(String user);

    long userActiveCount(String user);
}
