package com.edcccd.blog.service.serviceimpl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.edcccd.blog.dto.DtArticle;
import com.edcccd.blog.dto.DtArticleSmall;
import com.edcccd.blog.entity.Article;
import com.edcccd.blog.entity.ArticleBody;
import com.edcccd.blog.entity.Tag;
import com.edcccd.blog.mapper.ArticleMapper;
import com.edcccd.blog.service.ArticleBodyService;
import com.edcccd.blog.service.ArticleService;
import com.edcccd.blog.service.CategoryService;
import com.edcccd.blog.util.PageUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Resource
    ArticleMapper mapper;
    @Resource
    ArticleBodyService bodyService;
    @Resource
    CategoryService categoryService;
    @Resource
    TagServiceImpl tagServiceImpl;

    @Override
    public DtArticle getById(Long id) {
        Article article = mapper.selectById(id);
        if (article == null)
            return null;

        List<Tag> tags = tagServiceImpl.queryByArticle(article.getId());
        ArticleBody articleBody = bodyService.getByArticleId(article.getId());
        String category = categoryService.getByArticleId(article.getId());

        DtArticle dtArticle = new DtArticle(article);
        dtArticle.setTags(tags);
        dtArticle.setBody(articleBody.getTxt());
        dtArticle.setCategory(category);
        return dtArticle;
    }

    @Override
    public PageDTO<DtArticle> getByCategory(Long categoryId) {
        Page<DtArticle> page = PageUtils.getPage(DtArticle.class);
        mapper.getByCategory(page, categoryId);
        PageDTO<DtArticle> pageDTO = new PageDTO<>(page.getCurrent(), page.getSize());
        pageDTO.setRecords(page.getRecords());
        return pageDTO;
    }

    @Override
    public PageDTO<DtArticle> getByTag(Long tagId) {
        Page<DtArticle> page = PageUtils.getPage(DtArticle.class);
        mapper.getByTag(page, tagId);
        PageDTO<DtArticle> pageDTO = new PageDTO<>(page.getCurrent(), page.getSize());
        pageDTO.setRecords(page.getRecords());
        return pageDTO;
    }

    @Override
    public List<Article> list() {
        // todo 改成分页查询，临时随便查询
        return mapper.selectList(null);
    }

    @Override
    public void save(DtArticle article) {
        throw new RuntimeException("暂时没有实现");
    }

    @Override
    public void removeById(Long id) {
        Article article = mapper.selectById(id);
        if (article == null)
            return;

        mapper.deleteById(id);
        bodyService.removeByArticleId(id);
        tagServiceImpl.removeByArticleId(id);
    }

    @Override
    public void updateById(DtArticle article) {
        throw new RuntimeException("暂时没有实现");
    }

    @Override
    public Long count() {
        return mapper.selectCount(null);
    }

    @Override
    public List<DtArticleSmall> Latest(int num) {
        LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(Article::getCreateTime);
        wrapper.last("limit " + num);

        List<Article> articles = mapper.selectList(wrapper);

        return BeanUtil.copyToList(articles, DtArticleSmall.class);
    }

    @Override
    public PageDTO<DtArticle> page() {
        Page<DtArticle> page = PageUtils.getPage(DtArticle.class);

        mapper.page(page);
        PageDTO<DtArticle> pageDTO = new PageDTO<>(page.getCurrent(), page.getSize());

        List<DtArticle> dtArticles = page.getRecords().stream().peek(
                article -> article.setBody(StrUtil.subPre(article.getBody(), 100)))
                .collect(Collectors.toList());

        pageDTO.setRecords(dtArticles);
        return pageDTO;
    }
}

