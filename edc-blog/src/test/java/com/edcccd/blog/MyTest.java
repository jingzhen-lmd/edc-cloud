package com.edcccd.blog;

import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.edcccd.blog.dto.DtArticle;
import com.edcccd.blog.dto.DtCategory;
import com.edcccd.blog.service.ArticleService;
import com.edcccd.blog.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class MyTest {

    @Resource
    RedisTemplate<String, Object> redisTemplate;
    @Resource
    CategoryService service;
    @Resource
    ArticleService articleService;

    @Test
    public void test() {
        PageDTO<DtArticle> byCategory = articleService.getByCategory(3L);

        System.out.println(byCategory);
    }

    @Test
    public void test2() {
//        Long count = service.count();
//        service.listAll();
//        System.out.println("asd");
//        Category category =new Category();
//        category.setName("jingji");
//        category.setCreateTime(new Date());
//        service.create(category);

//        boolean jingji = service.isExist("jingji2");
//        boolean 日记 = service.isExist("日记2");
//        System.out.println(jingji);
//        System.out.println(日记);


        List<DtCategory> dtCategory = service.listArticleCount();
        System.out.println(dtCategory);

    }
}
