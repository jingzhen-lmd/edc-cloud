package com.edcccd.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 分类，暂时没什么用
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtCategory {
  private Long id;

  /**
   * 分类名
   */
  private String name;

  private Date createTime;

  /**
   * 此文章权限
   */
  private String power;

  /**
   * 此分类下文章数量
   */
  private Long articleCount;
}