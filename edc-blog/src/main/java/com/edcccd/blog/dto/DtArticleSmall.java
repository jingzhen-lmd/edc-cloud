package com.edcccd.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 轻量级文章
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtArticleSmall {
    private Long id;
    /**
     * 标题
     */
    private String title;

    /**
     * 副标题
     */
    private String subtitle;


    /**
     * 图片
     */
    private String picture;


    private Date createTime;

    /**
     * 此文章权限
     */
    private String power;
}