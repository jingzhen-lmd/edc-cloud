package com.edcccd.account.common.interceptor;

import com.edcccd.account.pojo.User;

/**
 * ThreadLocal工具类，用于保存登录的用户信息
 */
public class UserHolder {
    private static final ThreadLocal<User> tl = new ThreadLocal<>();

    public static void saveUser(User user){
        tl.set(user);
    }

    public static User getUser(){
        return tl.get();
    }

    public static void removeUser(){
        tl.remove();
    }
}
