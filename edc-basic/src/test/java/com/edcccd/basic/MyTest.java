package com.edcccd.basic;

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
