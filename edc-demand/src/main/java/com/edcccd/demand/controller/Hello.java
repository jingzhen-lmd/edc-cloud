package com.edcccd.demand.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * hello
 */
@RestController
public class Hello {


  @GetMapping("hello")
  public String tongji() {

    return "hello";
  }

}
