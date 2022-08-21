package com.edcccd.blog.config;

import com.edcccd.common.util.RedisUtil;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 点赞模块，用于点赞
 */
@RestController
public class StartConfig {

    @Resource
    RedisUtil redisUtil;


    /**
     *
     * @param model
     * @param id
     */
    public void start(String model, String id) {

    }


}
