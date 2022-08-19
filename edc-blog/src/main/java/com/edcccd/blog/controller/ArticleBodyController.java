package com.edcccd.blog.controller;

import com.edcccd.blog.entity.ArticleBody;
import com.edcccd.blog.service.serviceimpl.ArticleBodyServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 文章内容
 *
 * @author xxxxx
 */
@RestController
@RequestMapping("/article_body")
public class ArticleBodyController {
    /**
     * 服务对象
     */
    @Resource
    private ArticleBodyServiceImpl articleBodyServiceImpl;

    /**
     * 通过文章id查询
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public ArticleBody getByArticleId(Long id) {
        return articleBodyServiceImpl.getByArticleId(id);
    }

}
