package com.edcccd.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 分类，暂时没什么用
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "category")
public class Category {
  @TableId(value = "id", type = IdType.AUTO)
  private Long id;

  /**
   * 分类名
   */
  @TableField(value = "name")
  private String name;

  @TableField(value = "create_time")
  private Date createTime;

  /**
   * 此文章权限
   */
  @TableField(value = "power")
  private String power;

  /**
   * 删除=1
   */
  @TableField(value = "deleted")
  private String deleted;
}