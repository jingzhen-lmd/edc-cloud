package com.edcccd.blog.service.serviceimpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.edcccd.blog.entity.Article;
import com.edcccd.blog.mapper.ArticleMapper;
import com.edcccd.blog.service.ArticleService;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

}

