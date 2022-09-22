package com.edcccd.blog.util;

import com.edcccd.blog.entity.UserDetail;

/**
 * 用户工具类，保存用户数据
 **/
public class UserThreadLocal {

  private static final ThreadLocal<UserDetail> USER_HOLDER = new ThreadLocal<>();

  public static void setUser(UserDetail user) {
    USER_HOLDER.set(user);
  }

  public static UserDetail getUser() {
    return USER_HOLDER.get();
  }

  public static void remove() {
    USER_HOLDER.remove();
  }
}
