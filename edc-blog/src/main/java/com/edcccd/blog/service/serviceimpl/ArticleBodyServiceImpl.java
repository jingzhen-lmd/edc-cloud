package com.edcccd.blog.service.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.edcccd.blog.entity.ArticleBody;
import com.edcccd.blog.mapper.ArticleBodyMapper;
import com.edcccd.blog.service.ArticleBodyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ArticleBodyServiceImpl implements ArticleBodyService {

    @Autowired
    ArticleBodyMapper mapper;
    @Resource
    TagServiceImpl tagServiceImpl;

    @Override
    public ArticleBody getByArticleId(Long articleId) {
        LambdaQueryWrapper<ArticleBody> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ArticleBody::getArticleId, articleId);
        return mapper.selectOne(wrapper);
    }

    @Override
    public void removeByArticleId(Long articleId) {
        mapper.deleteById(articleId);
        tagServiceImpl.removeByArticleId(articleId);
    }
}
