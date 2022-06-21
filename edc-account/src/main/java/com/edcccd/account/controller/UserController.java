package com.edcccd.account.controller;

import com.edcccd.account.mapper.UserMapper;
import com.edcccd.account.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

  @Autowired
  UserMapper mapper;

  @GetMapping("hello")
  public String hello() {
    return "hello";
  }

  @GetMapping("user")
  public String listUser() {
    List<User> users = mapper.selectList(null);
    return users.toString();
  }

  @GetMapping("user/{id}")
  public String user(@PathVariable("id") String id) {
    User user = mapper.selectById(id);
    return user.toString();
  }

  @DeleteMapping("user")
  public int removeUser(@RequestBody User user) {
    return mapper.deleteById(user);
  }

  @PostMapping("user")
  public int addUser(@RequestBody User user) {
    return mapper.insert(user);
  }

  @PutMapping("user")
  public int updateUser(@RequestBody User user) {
    return mapper.updateById(user);
  }
}
