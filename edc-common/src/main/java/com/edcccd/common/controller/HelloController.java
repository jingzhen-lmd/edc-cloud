package com.edcccd.common.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

  @GetMapping("common")
  public String hello() {
    return "hello common";
  }
}
