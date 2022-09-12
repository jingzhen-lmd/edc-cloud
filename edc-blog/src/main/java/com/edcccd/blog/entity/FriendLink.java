package com.edcccd.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
    * 友情链接
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "friendlink")
public class FriendLink {
    @TableField(value = "id")
    private Long id;

    /**
     * 名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 头像
     */
    @TableField(value = "avatar")
    private String avatar;

    /**
     * 地址
     */
    @TableField(value = "address")
    private String address;

    /**
     * 介绍
     */
    @TableField(value = "intro")
    private String intro;

    @TableField(value = "create_time")
    private Date createTime;
}