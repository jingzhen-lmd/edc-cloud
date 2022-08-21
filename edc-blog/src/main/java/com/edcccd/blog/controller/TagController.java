package com.edcccd.blog.controller;

import com.edcccd.blog.entity.Tag;
import com.edcccd.blog.service.serviceimpl.TagServiceImpl;
import com.edcccd.common.util.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * (tag)表控制层
 *
 * @author xxxxx
 */
@RestController
@RequestMapping("/tag")
public class TagController {
    /**
     * 服务对象
     */
    @Resource
    private TagServiceImpl tagServiceImpl;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Tag selectOne(Long id) {
        return tagServiceImpl.getById(id);
    }

    /**
     * 查询所有标签
     */
    @GetMapping
    public Result<List<Tag>> listAll() {
        List<Tag> list = tagServiceImpl.listAll();
        return Result.success(list);
    }

}
