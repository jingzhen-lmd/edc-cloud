package com.edcccd.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 点赞表，用于记录用户点赞的内容
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "enjoy")
public class Enjoy {
  @TableId(value = "id", type = IdType.AUTO)
  private Long id;

  @TableField(value = "user_id")
  private Long userId;

  /**
   * 内容模块
   */
  @TableField(value = "model")
  private String model;

  /**
   * 喜欢内容的id
   */
  @TableField(value = "enjoy_contents")
  private String enjoyContents;
}