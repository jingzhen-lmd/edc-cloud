package com.edcccd.basic.service.controller;

import com.edcccd.account.api.feign.CheckClient;
import com.edcccd.basic.api.feign.UserFeign;
import com.edcccd.basic.api.pojo.User;
import com.edcccd.common.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class HelloController {

  @Autowired
  UserFeign userFeign;

  @Resource
  CheckClient checkClient;

  @GetMapping("check")
  public String check() {
    boolean asdasd = checkClient.checkToken("Asdasd");
    return "asdasdfd";
  }

  @GetMapping("hello")
  public String hello() {
    return userFeign.hello();
  }

  @GetMapping("basic")
  public String helloBasic() {
    return "helloBasic";
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
