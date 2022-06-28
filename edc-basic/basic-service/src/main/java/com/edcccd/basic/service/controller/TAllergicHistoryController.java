package com.edcccd.basic.service.controller;

import com.edcccd.basic.service.mapper.TAllergicHistoryMapper;
import com.edcccd.basic.api.pojo.table.TAllergicHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 身体检查
 */
@RequestMapping("allergicHistory")
@RestController
public class TAllergicHistoryController {

    @Autowired
    TAllergicHistoryMapper mapper;

    @GetMapping()
    List<TAllergicHistory> allergicHistories() {
        return mapper.selectList(null);
    }

    @GetMapping("{id}")
    TAllergicHistory allergicHistory(@PathVariable("id") String id) {
        return mapper.selectById(id);
    }

    @DeleteMapping("{id}")
    int deleteAllergicHistory(@PathVariable("id") String id) {
        return mapper.deleteById(id);
    }

    @PostMapping
    int allergicHistory(@RequestBody TAllergicHistory tAllergicHistory) {
        return mapper.insert(tAllergicHistory);
    }

    @PutMapping
    int updateAllergicHistory(@RequestBody TAllergicHistory tAllergicHistory) {
        return mapper.insert(tAllergicHistory);
    }
}
