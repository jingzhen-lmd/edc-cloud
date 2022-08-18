package com.edcccd.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
    * 文章主表
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "article")
public class Article {
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    /**
     * 文章明细
     */
    @TableField(value = "article_item")
    private Long articleItem;

    /**
     * 标题
     */
    @TableField(value = "title")
    private String title;

    /**
     * 副标题
     */
    @TableField(value = "subtitle")
    private String subtitle;

    /**
     * 图片
     */
    @TableField(value = "picture")
    private String picture;

    /**
     * 类型
     */
    @TableField(value = "`type`")
    private String type;

    @TableField(value = "creator_id")
    private Long creatorId;

    @TableField(value = "creator_name")
    private String creatorName;

    @TableField(value = "create_time")
    private Date createTime;

    @TableField(value = "updator_id")
    private Long updatorId;

    @TableField(value = "updator_name")
    private String updatorName;

    @TableField(value = "update_time")
    private Date updateTime;

    /**
     * 此文章权限
     */
    @TableField(value = "`power`")
    private String power;

    /**
     * 删除=1
     */
    @TableField(value = "deleted")
    private String deleted;
}