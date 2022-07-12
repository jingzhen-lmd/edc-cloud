package com.edcccd.demand.command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MyUtil {

    /**
     * 用于redis生成日期相关的key
     *
     * @param pre 前缀
     * @param key key
     * @return 前缀+日期+key
     */
    public static String getDateKey(String pre, String key) {
        String format = LocalDate.now().format(DateTimeFormatter.ofPattern(Const.REDIS_FORMAT));
        return pre + ":" + format + ":" + key;
    }

    /**
     * 用于redis相关的key
     *
     * @param pre 前缀
     * @param key key
     * @return 前缀+日期+key
     */
    public static String getKey(String pre, String key) {
        return pre + ":" + key;
    }


}
