package com.edcccd.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 首页配置信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtHomeInfo {
    // 博客名
    private String blogName;
    // 留言
    private String comments;

    private Long articleCount;
    private Long categoryCount;
    private Long tagCount;

    // 公告
    private String pageNotice;
    // 总访问量
    private String visitCount;

}