package com.edcccd.basic.service.controller;

import com.edcccd.basic.service.mapper.TPhysicalExamItemMapper;
import com.edcccd.basic.service.mapper.TPhysicalExamMapper;
import com.edcccd.basic.api.pojo.table.TPhysicalExam;
import com.edcccd.basic.api.pojo.table.TPhysicalExamItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 身体检查
 */
@RestController
@RequestMapping("physicalExam")
public class PhysicalExamController {

    @Autowired
    TPhysicalExamMapper mapper;
    @Autowired
    TPhysicalExamItemMapper itemMapper;

    @GetMapping("physicalExam")
    List<TPhysicalExam> physicalExams() {
        return mapper.selectList(null);
    }

    @GetMapping("physicalExam/{id}")
    TPhysicalExam physicalExam(@PathVariable("id") String id) {
        return mapper.selectById(id);
    }


    //    ---------------明细-----------------------
    @GetMapping("physicalExamItem")
    List<TPhysicalExamItem> physicalExamItems() {
        return itemMapper.selectList(null);
    }

    @GetMapping("physicalExamItem/{id}")
    TPhysicalExamItem physicalExamItem(@PathVariable("id") String id) {
        return itemMapper.selectById(id);
    }

}
