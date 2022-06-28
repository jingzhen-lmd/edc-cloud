package com.edcccd.account.controller;

import com.edcccd.account.common.Result;
import com.edcccd.account.mapper.CenterMapper;
import com.edcccd.account.pojo.Center;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("account/center")
public class CenterController {

    @Autowired
    CenterMapper mapper;

    @GetMapping("{id}")
    public Result<Center> getCenter(@PathVariable("id") String id) {
        Center center = mapper.selectById(id);
        return Result.success(center);
    }

    @GetMapping
    public Result<List<Center>> listCenter() {
        List<Center> centers = mapper.selectList(null);
        return Result.success(centers);
    }
}
