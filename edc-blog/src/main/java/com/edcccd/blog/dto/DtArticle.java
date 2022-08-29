package com.edcccd.blog.dto;

import cn.hutool.core.bean.BeanUtil;
import com.edcccd.blog.entity.Article;
import com.edcccd.blog.entity.ArticleBody;
import com.edcccd.blog.entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * 文章主表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtArticle {
    private Long id;

    /**
     * 文章内容
     */
    private ArticleBody articleBody;

    /**
     * 标题
     */
    private String title;

    /**
     * 副标题
     */
    private String subtitle;

    /**
     * 正文
     */
    private String body;

    /**
     * 标签组
     */
    private List<Tag> tags;

    /**
     * 图片
     */
    private String picture;

    /**
     * 类型
     */
    private String type;

    private Long creatorId;

    private String creatorName;

    private Date createTime;

    private Long updatorId;

    private String updatorName;

    private Date updateTime;

    /**
     * 此文章权限
     */
    private String power;

    /**
     * 删除=1
     */
    private String deleted;

    public DtArticle(Article article) {
        BeanUtil.copyProperties(article, this);
    }
}