package com.edcccd.blog.controller;

import com.edcccd.blog.dto.DtCategory;
import com.edcccd.blog.entity.Category;
import com.edcccd.blog.service.CategoryService;
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
@RequestMapping("/category")
public class CategoryController {
    @Resource
    private CategoryService service;

    @GetMapping
    public Result<List<Category>> listAll() {
        List<Category> categories = service.listAll();
        return Result.success(categories);
    }

    @GetMapping("listArticleCount")
    public Result<List<DtCategory>> listArticleCount() {
        List<DtCategory> dtCategories = service.listArticleCount();
        return Result.success(dtCategories);
    }

    @GetMapping("count")
    public Result<Long> count() {
        Long count = service.count();
        return Result.success(count);
    }


}
