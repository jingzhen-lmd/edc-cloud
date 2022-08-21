package com.edcccd.blog.service.serviceimpl;

import cn.hutool.core.util.StrUtil;
import com.edcccd.blog.config.Model;
import com.edcccd.blog.service.StartService;
import com.edcccd.common.util.RedisUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.edcccd.blog.config.Constant.BLOG;

@Service
public class StartServiceImpl implements StartService {

    @Resource
    private RedisUtil redisUtil;

    @Override
    public void start(String prefix, String id) {
        // 获取用户id
        // 获取用户文章
        // 查询该用户的点赞结构
        // 没有点赞则点在文章
        // 点赞文章则取消点赞

        //    对饮文章的点赞数量也应该变化
    }

    @Override
    public Integer likeCount(Model model, Long id) {
        String likeCount = redisUtil.getString(BLOG + model.name() + ":" + id);
        return StrUtil.isNotBlank(likeCount) ? Integer.parseInt(likeCount) : 0;
    }
}
