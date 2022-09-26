package com.edcccd.common.util;

import java.time.LocalDateTime;

/**
 * 系统静态常量
 */
public class Const {
    /**
     * 程序起始日期
     */
    public static final LocalDateTime LOCAL_DATE_TIME;

    /**
     * 用户信息
     */
    public static final String USERINFO = "userInfo";

    static {
        LOCAL_DATE_TIME = LocalDateTime.of(2022, 7, 1, 0, 0);
    }
}
