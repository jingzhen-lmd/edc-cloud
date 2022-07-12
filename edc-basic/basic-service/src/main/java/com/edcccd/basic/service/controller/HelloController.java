package com.edcccd.basic.service.controller;

import com.edcccd.basic.api.feign.UserFeign;
import com.edcccd.basic.api.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloController {

  @Autowired
  UserFeign userFeign;


  @GetMapping("hello")
  public String hello() {
    return userFeign.hello();
  }

  @GetMapping("user")
  List<User> listUser() {
    return userFeign.listUser();
  }

  @GetMapping("user/{id}")
  String user(@PathVariable("id") String id) {
    return userFeign.user(id);
  }

}
