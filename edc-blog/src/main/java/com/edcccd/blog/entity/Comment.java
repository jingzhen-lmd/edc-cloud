package com.edcccd.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.edcccd.blog.util.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 评论
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "`comment`")
public class Comment {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 父评论id
     */
    @TableField(value = "pid")
    private Long pid;

    /**
     * 对象的类型
     */
    @TableField(value = "model")
    private Model model;

    /**
     * 对象id
     */
    @TableField(value = "target_id")
    private String targetId;

    /**
     * 用户id
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 评论内容
     */
    @TableField(value = "txt")
    private String txt;

    @TableField(value = "creator_id")
    private Long creatorId;

    @TableField(value = "creator_name")
    private String creatorName;

    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 第几层
     */
    @TableField(value = "layer")
    private Integer layer;

    /**
     * 删除=1
     */
    @TableField(value = "deleted")
    private String deleted;
}