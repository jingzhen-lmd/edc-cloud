package com.edcccd.common.service.serviceImpl;

import com.edcccd.common.service.FansService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class FansServiceImpl implements FansService {

    @Autowired
    StringRedisTemplate template;

    @Override
    public boolean isLike(String id) {


        return false;
    }


    @Override
    public boolean like(String id) {
    //    1、获取当前用户id

    //    2、判断当前用户是否点赞
    //    点赞则取消
    //    没电则点赞
    //     数据库对应变化


    }
}
