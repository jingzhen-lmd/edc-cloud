package com.edcccd.blog.controller;

import com.edcccd.blog.entity.Article;
import com.edcccd.blog.service.ArticleService;
import com.edcccd.common.util.Result;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.util.List;

/**
 * (article)表控制层
 *
 * @author xxxxx
 */
@RestController
@RequestMapping("/article")
@Api(tags = "文章方法")
public class ArticleController {

    /**
     * 服务对象
     */
    @Resource
    private ArticleService articleService;

    /**
     * 通过主键查询单条数据
     *
     * @param articleId 主键
     * @return 单条数据
     */
    @GetMapping("{articleId}")
    public Result<Article> selectOne(@PathVariable("articleId") Integer articleId) {
        Article article = articleService.getById(articleId);
        return Result.success(article);
    }

    /**
     * 用于首页的分页查询
     */
    @GetMapping
    public Result<List<Article>> query() {
        List<Article> list = articleService.list();
        return Result.success(list);
    }

    /**
     * 发布文章
     *
     * @param article 文章
     */
    @PostMapping
    public Result<Void> publish(@RequestBody Article article) {
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
    public Result<Void> remove(@RequestBody String articleId) {
        articleService.removeById(articleId);
        return Result.success();
    }

    /**
     * 更新文章
     *
     * @param article 文章
     */
    @PutMapping
    public Result<Void> update(@RequestBody Article article) {
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
