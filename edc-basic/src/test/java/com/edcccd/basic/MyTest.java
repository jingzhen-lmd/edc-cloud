package com.edcccd.basic;/*
 * @ 版权所有(C)，上海海鼎信息工程股份有限公司，2021，所有权利保留。
 * @ 项目名：	edc-cloud
 * @ 文件名：
 * @ 模块说明：
 * @ 修改历史：
 * @ 2022/6/22- jingzhen - 创建。
 */

import com.edcccd.basic.pojo.Student;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class MyTest {

  @Resource
  Student student3;

  @Test
  public void zhuru() {

    System.out.println(student3);
  }
}
