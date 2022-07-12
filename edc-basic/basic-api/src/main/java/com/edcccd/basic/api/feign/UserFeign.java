package com.edcccd.basic.api.feign;

import com.edcccd.basic.api.config.FeignConfig;
import com.edcccd.basic.api.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@FeignClient(value = "userService", url = "http://43.142.160.234:8091/")
@FeignClient(value = "userService", url = "http://localhost:8091/account",configuration = FeignConfig.class)
public interface UserFeign {

  @GetMapping("hello")
  String hello();

  @GetMapping("user")
  List<User> listUser();

  @GetMapping("users")
  List<User> listUser(List<String> ids);

  @GetMapping("user/{id}")
  String user(@PathVariable("id") String id);

  @DeleteMapping("user")
  int removeUser(@RequestBody User user);

  @PostMapping("user")
  int addUser(@RequestBody User user);

  @PutMapping("user")
  int updateUser(@RequestBody User user);
}
