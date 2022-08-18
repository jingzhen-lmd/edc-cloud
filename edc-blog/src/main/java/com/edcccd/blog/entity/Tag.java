package com.edcccd.blog.entity;

import lombok.Data;

/**
 * 标签，用于说明文章标签类型
 */
@Data
public class Tag {
  private Long id;
  /**
   * 标签名称
   */
  private String name;
}