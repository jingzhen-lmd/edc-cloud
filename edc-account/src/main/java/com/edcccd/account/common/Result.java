package com.edcccd.account.common;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * controller返回值封装
 */
@Data
@Accessors(chain = true)//链式操作
public class Result<T> {
    /**
     * 状态码
     */
    private String code;
    /**
     * 信息
     */
    private String message;
    /**
     * 返回数据,json格式
     */
    private T data;

    /**
     * 构造方法
     */
    private Result() {
    }


    /**
     * 构造方法
     */
    public static <T> Result<T> success(T data) {
        return new Result<T>().setCode("200").setMessage("访问成功").setData(data);
    }

    public static <T> Result<T> success() {
        return new Result<T>().setCode("200").setMessage("访问成功");
    }

    public static <T> Result<T> fail(String code, String message) {
        return new Result<T>().setCode(code).setMessage(message);
    }

    public static <T> Result<T> fail_null(String data) {
        return new Result<T>().setCode("400").setMessage("传入的参数:" + data + "为空");
    }
}
