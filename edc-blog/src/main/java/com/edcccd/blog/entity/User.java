package com.edcccd.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 用户表，登录账号
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    /**
     * 账号
     */
    private String userName;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 密码
     */
    private String password;

    /**
     * 电话号码
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 0女1男2未知
     */
    private String sex;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 角色
     */
    private String role;

    /**
     * 账号状态
     */
    private String status;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    /**
     * 1=删除
     */
    private String deleted;
}