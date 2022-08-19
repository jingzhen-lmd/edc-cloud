package com.edcccd.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
    * 文章正文对象
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "article_body")
public class ArticleBody {
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    /**
     * 文章id
     */
    @TableField(value = "article_id")
    private Long articleId;

    /**
     * 正文
     */
    @TableField(value = "txt")
    private String txt;
}