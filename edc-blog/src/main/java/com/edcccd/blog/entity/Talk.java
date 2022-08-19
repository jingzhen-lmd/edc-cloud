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
 * 说说，相当于发朋友圈，不正式的文章
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "talk")
public class Talk {
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    /**
     * 文本内容
     */
    @TableField(value = "txt")
    private String txt;

    /**
     * 附图
     */
    @TableField(value = "picture")
    private String picture;

    /**
     * 用户id
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 是否置顶
     */
    @TableField(value = "is_top")
    private String isTop;

    @TableField(value = "creator_id")
    private Long creatorId;

    @TableField(value = "creator_name")
    private String creatorName;

    @TableField(value = "create_time")
    private Date createTime;

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