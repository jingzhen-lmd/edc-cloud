package com.edcccd.common.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 测试类，无用
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student implements Serializable {
  private int id;
  private String name;
  private int age;
}
