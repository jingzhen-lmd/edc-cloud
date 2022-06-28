package com.edcccd.basic.controller;

import com.edcccd.basic.mapper.TBloodBiochemistryMapper;
import com.edcccd.basic.pojo.table.TBloodBiochemistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 血生化检查
 */
@RestController
@RequestMapping("basic/bloodBiochemistry")
public class TBloodBiochemistryController {

  @Autowired
  TBloodBiochemistryMapper mapper;

  // 不含明细
  @GetMapping("{id}")
  TBloodBiochemistry bloodBiochemistry(@PathVariable("id") long id) {
    return mapper.selectById(id);
  }

  // 查询所有，应该用不到
  @GetMapping
  List<TBloodBiochemistry> bloodBiochemistry() {
    return mapper.selectList(null);
  }


  //    ---------------明细-----------------------
//    @GetMapping("physicalExamItem")
//    List<TPhysicalExamItem> physicalExamItems() {
//        return itemMapper.selectList(null);
//    }


}
