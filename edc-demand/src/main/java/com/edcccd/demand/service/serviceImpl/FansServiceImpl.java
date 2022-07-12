package com.edcccd.demand.service.serviceImpl;

import cn.hutool.core.util.StrUtil;
import com.edcccd.basic.api.feign.UserFeign;
import com.edcccd.basic.api.pojo.User;
import com.edcccd.demand.command.Const;
import com.edcccd.demand.command.MyUtil;
import com.edcccd.demand.mapper.VideoMapper;
import com.edcccd.demand.pojo.Video;
import com.edcccd.demand.service.FansService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Slf4j
@Component
public class FansServiceImpl implements FansService {

    @Autowired
    StringRedisTemplate template;
    @Autowired
    VideoMapper videoMapper;
    @Autowired
    UserFeign userFeign;


    @Override
    public boolean isLike(String user, String id) {
        assert StrUtil.isNotBlank(user);
        assert StrUtil.isNotBlank(id);

        String key = MyUtil.getDateKey(Const.VIDEO_KEY, id);
        Double score = template.opsForZSet().score(key, user);

        return score != null;
    }


    @Override
    public boolean like(String user, String id) {
        assert StrUtil.isNotBlank(user);
        assert StrUtil.isNotBlank(id);
        boolean result = false;

        Video video = videoMapper.selectById(id);
        if (video == null) {
            log.warn("视频不存在,访问用户{}，访问video{}", user, id);
            return result;
        }

        String key = MyUtil.getDateKey(Const.VIDEO_KEY, id);
        Double score = template.opsForZSet().score(key, user);

        //  判断当前用户是否点赞
        if (score != null) {
            //    点赞则取消
            template.opsForZSet().remove(key, user);
            video.setFansNum(video.getFansNum() - 1);
            result = false;
        } else {
            //    没点则点赞
            template.opsForZSet().add(key, user, System.currentTimeMillis());
            video.setFansNum(video.getFansNum() + 1);
            result = true;
        }
        //     数据库对应变化
        videoMapper.updateById(video);
        return result;
    }

    @Override
    public List<User> listFans(String id) {
        assert StrUtil.isNotBlank(id);

        String key = MyUtil.getDateKey(Const.VIDEO_KEY, id);
        Set<String> members = template.opsForZSet().rangeByLex(key, new RedisZSetCommands.Range());
        if (members==null){
            return new ArrayList<>();
        }
        return userFeign.listUser(new ArrayList<>(members));
    }
}
