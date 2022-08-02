package com.edcccd.account.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName(value = "`user`")
public class User {
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    /**
     * 账号
     */
    @TableField(value = "user_name")
    private String userName;

    /**
     * 昵称
     */
    @TableField(value = "nick_name")
    private String nickName;

    /**
     * 密码
     */
    @TableField(value = "`password`")
    private String password;

    /**
     * 电话号码
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 邮箱
     */
    @TableField(value = "email")
    private String email;

    /**
     * 0女1男
     */
    @TableField(value = "sex")
    private String sex;

    /**
     * 头像
     */
    @TableField(value = "avatar")
    private String avatar;

    /**
     * 角色
     */
    @TableField(value = "`role`")
    private String role;

    /**
     * 账号状态
     */
    @TableField(value = "`status`")
    private String status;

    @TableField(value = "create_by")
    private String createBy;

    @TableField(value = "create_time")
    private Date createTime;

    @TableField(value = "update_by")
    private String updateBy;

    @TableField(value = "update_time")
    private Date updateTime;

    /**
     * 1=删除
     */
    @TableField(value = "deleted")
    private String deleted;
}