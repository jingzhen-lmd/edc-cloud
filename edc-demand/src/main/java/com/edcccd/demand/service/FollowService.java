package com.edcccd.demand.service;

import java.util.List;

/**
 * 关注服务
 */
public interface FollowService {

    boolean isFollow(String user, String id);

    boolean follow(String user, String id);

    List<String> myNvShen(String user);

    List<String> commonFriend(String user, String id);
}
