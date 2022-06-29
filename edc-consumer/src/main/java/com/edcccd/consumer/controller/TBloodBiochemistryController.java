package com.edcccd.consumer.controller;

import com.edcccd.consumer.feign.BloodBiochemistryFeign;
import com.edcccd.consumer.pojo.TBloodBiochemistry;
import com.edcccd.consumer.service.TBloodBiochemistryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 血生化检查
 */
@RestController
@RequestMapping("consumer/bloodBiochemistry")
public class TBloodBiochemistryController {

  @Autowired
  BloodBiochemistryFeign feign;
  @Autowired
  TBloodBiochemistryService service;


  // 不含明细
  @GetMapping("{id}")
  TBloodBiochemistry bloodBiochemistry(@PathVariable("id") long id) {
    return service.getById(id);
  }

  // 查询所有，应该用不到
  @GetMapping
  List<TBloodBiochemistry> bloodBiochemistry() {
    return feign.bloodBiochemistry();
  }

  @PutMapping
  Boolean bloodBiochemistry(@RequestBody TBloodBiochemistry tBloodBiochemistry) {
    return service.bloodBiochemistry(tBloodBiochemistry);
  }


}
