package com.edcccd.blog.service.serviceimpl;

import cn.hutool.core.bean.BeanUtil;
import com.edcccd.blog.dto.DtHomeInfo;
import com.edcccd.blog.mapper.ConfigMapper;
import com.edcccd.blog.service.ArticleService;
import com.edcccd.blog.service.CategoryService;
import com.edcccd.blog.service.ConfigService;
import com.edcccd.blog.service.TagService;
import com.edcccd.common.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.concurrent.TimeUnit;

import static com.edcccd.blog.config.Constant.*;

@Service
public class ConfigServiceImpl implements ConfigService {

    @Resource
    ConfigMapper mapper;
    @Resource
    RedisUtil redisUtil;
    @Autowired
    ArticleService articleService;
     @Autowired
    CategoryService categoryService;
    @Autowired
    TagService TagService;

    @Override
    public DtHomeInfo homeInfo() {
        // 查询redis中是否存在数据，不存在去数据库中查
        DtHomeInfo info = redisUtil.getHashByCashThrow(HOME_INFO, DtHomeInfo.class);

        if (!BeanUtil.isEmpty(info)) {
            return info;
        }

        // 查询后写入redis
        info = new DtHomeInfo();
        info.setBlogName(mapper.getValue(BLOG_NAME));
        info.setComments(mapper.getValue(COMMENTS));
        info.setArticleCount(articleService.count());
        info.setCategoryCount(categoryService.count());
        info.setTagCount(TagService.count());
        info.setPageNotice(mapper.getValue(PAGE_NOTICE));
        info.setVisitCount(mapper.getValue(VISIT_COUNT));

        redisUtil.addMap(HOME_INFO, info,100, TimeUnit.MINUTES);

        return info;
    }
}
