package com.edcccd.common.controller;

import com.edcccd.common.pojo.Student;
import com.edcccd.common.util.MyRedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试类，无用
 */
@RestController
public class HelloController {

  @Autowired
  MyRedisUtil myRedisUtil;

  @GetMapping("common")
  public String hello() {
    return "hello common";
  }

  @GetMapping("addCash")
  public String add() {
    boolean b = myRedisUtil.addCache("addCash", "增加缓存值");

    return String.valueOf(b);
  }

  @GetMapping("addCash2")
  public void addEntity() {
    Student student = new Student(1, "wahahah", 2);
    myRedisUtil.addMap("student:" + student.getId(), student);

    student = new Student(2, "小明同学", 12);
    myRedisUtil.addMap("student:" + student.getId(), student);


//    return String.valueOf(b);
  }


}


