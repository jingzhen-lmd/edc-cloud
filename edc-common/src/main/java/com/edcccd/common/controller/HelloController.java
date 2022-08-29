package com.edcccd.common.controller;

import com.edcccd.common.pojo.Student;
import com.edcccd.common.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * 测试类，无用
 */
@RestController
public class HelloController {

  @Autowired
  RedisUtil redisUtil;

  @GetMapping("common")
  public String hello() {
    return "hello common";
  }

  @GetMapping("addCash")
  public String add() {
    redisUtil.addCache("addCash", "增加缓存值");

    return "增加成功！！！！";
  }

  @GetMapping("addCash2")
  public void addEntity() {
    Student student = new Student(1, "wahahah", 2);
    redisUtil.addMap("student:" + student.getId(), student,100, TimeUnit.MINUTES);

    student = new Student(2, "小明同学", 12);
    redisUtil.addMap("student:" + student.getId(), student,100, TimeUnit.MINUTES);


//    return String.valueOf(b);
  }


}


