package com.edcccd.blog.config;

/**
 * 本模块公用常量
 */
public class Constant {
    /**
     * 当前页码
     */
    public static final String CURRENT = "current";
    /**
     * 页码条数
     */
    public static final String SIZE = "size";
    /**
     * 默认条数
     */
    public static final String DEFAULT_SIZE = "10";


    /******************** redis缓存 *********************/
    public static final String BLOG = "blog:";
    /**
     * 博客浏览量
     */
    public static final String BLOG_VIEWS_COUNT = "block:blog_views_count";
    /**
     * 说说点赞量
     */
    public static final String TALK_LIKE_COUNT = "block:talk_like_count:";
    /**
     * 评论点赞量
     */
    public static final String COMMENT_LIKE_COUNT = "block:comment_like_count";
    /**
     * 文章点赞量
     */
    public static final String ARTICLE_LIKE_COUNT = "block:article_like_count";
    /**
     * 文章浏览量
     */
    public static final String ARTICLE_VIEWS_COUNT = "block:article_views_count";

}
