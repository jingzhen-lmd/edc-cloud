package com.edcccd.blog.util;

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

    /**
     * 用户信息
     */
    public static final String USERINFO = "userInfo";


    /******************** redis缓存 *********************/
    public static final String BLOG = "blog:";
    /**
     * 博客浏览量
     */
    public static final String BLOG_VIEWS_COUNT = "block:blog_views_count";
    /**
     * 点赞量
     */
    public static final String ENJOY_COUNT = "blog:enjoy_count:";
    /**
     * 文章浏览量
     */
    public static final String ARTICLE_VIEWS_COUNT = "block:article_views_count";


    /******************** 数据库config中key值 *********************/
    public static final String HOME_INFO = "blog:HomeInfo";

    public static final String BLOG_NAME = "blogName";
    /**
     * 留言
     */
    public static final String COMMENTS = "comments";
    /**
     * 网站公告
     */
    public static final String PAGE_NOTICE = "pageNotice";
    /**
     * 总访问数量
     */
    public static final String VISIT_COUNT = "visitCount";


}
