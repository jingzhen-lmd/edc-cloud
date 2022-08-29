package com.edcccd.blog.entity;

import lombok.Data;

/**
 * 配置类
 */
@Data
public class Config {
    private Long id;

    private String project;
    private String key;
    private String value;
    private String note;
}