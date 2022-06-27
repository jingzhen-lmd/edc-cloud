package com.edcccd.consumer.controller;

import com.edcccd.consumer.feign.BaseTableFeign;
import com.edcccd.consumer.pojo.TBloodBiochemistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 统计服务
 */
@RestController
public class Statistics {

  @Autowired
  BaseTableFeign feign;

  @GetMapping("tongji")
  public String tongji() {
    List<TBloodBiochemistry> tBloodBiochemistries = feign.bloodBiochemistry();

    return tBloodBiochemistries.toString();
  }

  @GetMapping("tongji/{id}")
  public String tongji(@PathVariable("id") long id) {
    TBloodBiochemistry tBloodBiochemistries = feign.bloodBiochemistry(id);

    return tBloodBiochemistries.toString();
  }
}
