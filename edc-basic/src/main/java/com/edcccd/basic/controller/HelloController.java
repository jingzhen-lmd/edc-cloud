package com.edcccd.basic.controller;

import com.edcccd.basic.feign.UserFeign;
import com.edcccd.basic.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

  @Autowired
  UserFeign userFeign;


  @GetMapping("hello")
  public String hello() {
    return userFeign.hello();
  }

  @GetMapping("user")
  String listUser() {
    return userFeign.listUser();
  }

  @GetMapping("user/{id}")
  String user(@PathVariable("id") String id) {
    return userFeign.user(id);
  }

}