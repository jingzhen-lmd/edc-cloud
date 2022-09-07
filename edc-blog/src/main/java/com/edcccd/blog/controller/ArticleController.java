package com.edcccd.blog.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.edcccd.blog.dto.DtArticle;
import com.edcccd.blog.dto.DtArticleSmall;
import com.edcccd.blog.service.ArticleService;
import com.edcccd.common.util.Result;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.util.List;

/**
 * 文章方法
 */
@RestController
@RequestMapping("/article")
@Api(tags = "文章方法")
public class ArticleController {

    @Resource
    private ArticleService articleService;

    /**
     * 通过主键查询单条数据
     *
     * @param articleId 主键
     * @return 单条数据
     */
    @GetMapping("{articleId}")
    public Result<DtArticle> selectOne(@PathVariable("articleId") Long articleId) {
        DtArticle article = articleService.getById(articleId);
        return Result.success(article);
    }

    /**
     * 用于首页的分页查询
     */
    @GetMapping
    public Result<PageDTO<DtArticle>> page() {
        PageDTO<DtArticle> list = articleService.page();
        return Result.success(list);
    }

    /**
     * 按类型进行分页查询
     */
    @GetMapping
    public Result<PageDTO<DtArticle>> pageByCategory(Long categoryId) {
        PageDTO<DtArticle> list = articleService.getByCategory(categoryId);
        return Result.success(list);
    }

    /**
     * 查询最新的文章（文章详情页推荐）
     *
     * @param num 数量
     */
    @GetMapping("latest")
    public Result<List<DtArticleSmall>> Latest(int num) {
        List<DtArticleSmall> list = articleService.Latest(num);
        return Result.success(list);
    }

    /**
     * 文章总数
     */
    @GetMapping("count")
    public Result<String> count() {
        Long num = articleService.count();
        return Result.success(String.valueOf(num));
    }

    /**
     * 发布文章
     *
     * @param article 文章
     */
    @PostMapping
    public Result<Void> publish(@RequestBody DtArticle article) {
        // 文章图片的处理
        articleService.save(article);
        return Result.success();
    }

    /**
     * 删除文章
     *
     * @param articleId 文章id
     */
    @ApiIgnore
    @DeleteMapping
    public Result<Void> remove(@RequestBody Long articleId) {
        articleService.removeById(articleId);
        return Result.success();
    }

    /**
     * 更新文章
     *
     * @param article 文章
     */
    @PutMapping
    public Result<Void> update(@RequestBody DtArticle article) {
        articleService.updateById(article);
        return Result.success();
    }

    /**
     * 查询用户发布的文章信息汇总，用以首页展示
     *
     * @param userId 用户id
     */
    @GetMapping("info")
    public Result<String> info(@RequestParam("userId") String userId) {
        // 用户信息直接从缓存中取出即可

        // 三个sql并行去查，然后返回，组装为数据信息。

        // 返回值需要封装为结构
        return Result.success();
    }

}
