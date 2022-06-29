package com.edcccd.consumer.feign;

import com.edcccd.consumer.pojo.TBloodBiochemistry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "baseService", contextId = "bloodService",
    url = "http://localhost:8092/basic/bloodBiochemistry")
public interface BloodBiochemistryFeign {

  // 不含明细
  @GetMapping("{id}")
  TBloodBiochemistry bloodBiochemistry(@PathVariable("id") long id);

  // 查询所有，应该用不到
  @GetMapping
  List<TBloodBiochemistry> bloodBiochemistry();

  @PutMapping
  Boolean bloodBiochemistry(@RequestBody TBloodBiochemistry tBloodBiochemistry);

}
