package com.edcccd.blog.controller;

import com.edcccd.blog.entity.Article;
import com.edcccd.common.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试类
 */
@RestController
@Slf4j
public class TestController {


    @PostMapping("test01")
    public Result<String> ceshi(@RequestBody Article article) {
        log.info("访问成功,上传的文章{}", article);
        return Result.success("访问成功");
    }
}
