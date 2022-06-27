package com.edcccd.consumer.feign;

import com.edcccd.consumer.pojo.TBloodBiochemistry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "baseService",url = "http://localhost:8092/basic/bloodBiochemistry")
public interface BaseTableFeign {

  // 不含明细
  @GetMapping("{id}")
  TBloodBiochemistry bloodBiochemistry(@PathVariable("id") long id);

  // 查询所有，应该用不到
  @GetMapping
  List<TBloodBiochemistry> bloodBiochemistry();

}
