package com.edcccd.blog;

import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.edcccd.account.api.entity.User;
import com.edcccd.blog.dto.DtArticle;
import com.edcccd.blog.dto.DtCategory;
import com.edcccd.blog.entity.Category;
import com.edcccd.blog.entity.UserDetail;
import com.edcccd.blog.service.ArticleService;
import com.edcccd.blog.service.CategoryService;
import com.edcccd.blog.service.LikeService;
import com.edcccd.blog.util.Model;
import com.edcccd.blog.util.UserThreadLocal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
// @Transactional
public class MyTest {

    @Resource
    RedisTemplate<String, Object> redisTemplate;
    @Resource
    CategoryService service;
    @Resource
    ArticleService articleService;
    @Autowired
    LikeService likeService;

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

    @Test
    public void huigun() throws Exception {
        Category category = new Category();
        category.setId(11L);
        category.setName("kankan能不能插入4");
        service.updateById(category);

        System.out.println("Asdas");
    }

    @Test
    public void 测试机() throws Exception {
        Long aLong = likeService.likeCount(Model.TALK, 1L);
        System.out.println(aLong);
    }

    @BeforeEach
    private void regist(){
        UserDetail userDetail = new UserDetail();
        User user = new User();
        user.setId(1L);
        user.setUserName("ccd");
        user.setId(1L);
        userDetail.setUser(user);
        ArrayList<String> list = new ArrayList<>();
        list.add("visit:delete");
        userDetail.setPowers(list);
        UserThreadLocal.setUser(userDetail);
    }
}
