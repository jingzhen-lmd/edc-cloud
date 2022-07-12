package com.edcccd.demand.service.serviceImpl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.edcccd.basic.api.feign.UserFeign;
import com.edcccd.demand.command.Const;
import com.edcccd.demand.command.MyUtil;
import com.edcccd.demand.mapper.FollowMapper;
import com.edcccd.demand.pojo.Follow;
import com.edcccd.demand.service.FollowService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Component
public class FollowServiceImpl implements FollowService {

    @Autowired
    StringRedisTemplate template;
    @Autowired
    FollowMapper followMapper;
    @Autowired
    UserFeign userFeign;

    @Override
    public boolean isFollow(String user, String id) {
        if (StrUtil.isBlank(user) || StrUtil.isBlank(id)) {
            log.warn("找不到关系user：{}，女神{}", user, id);
            return false;
        }
        Follow follow = getFollow(user, id);
        return follow != null;
    }

    private Follow getFollow(String user, String id) {
        QueryWrapper<Follow> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", user);
        queryWrapper.eq("follow_id", id);
        Follow follow = followMapper.selectOne(queryWrapper);
        return follow;
    }

    @Override
    public boolean follow(String user, String id) {
        if (StrUtil.isBlank(user) || StrUtil.isBlank(id)) {
            log.warn("找不到关系user：{}，女神{}", user, id);
            return false;
        }
        String key = MyUtil.getKey(Const.FOLLOW_KEY, user);

        //查询
        Follow existFollow = getFollow(user, id);

        if (existFollow != null) {
            //关注==取关返回false
            followMapper.deleteById(existFollow);
            //redis删除
            template.opsForZSet().remove(key, id);
            return false;
        } else {
            //没关注==关注返回true
            Follow follow = new Follow(Long.parseLong(user), Long.parseLong(id));
            followMapper.insert(follow);
            //redis存一份
            template.opsForSet().add(key, id);
            return true;
        }
    }

    @Override
    public List<String> myNvShen(String user) {
        if (StrUtil.isBlank(user)) {
            log.warn("找不到关系user：{}", user);
            return new ArrayList<>();
        }
        String key = MyUtil.getKey(Const.FOLLOW_KEY, user);

        QueryWrapper<Follow> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", user);
        List<Follow> follows = followMapper.selectList(queryWrapper);

        List<String> followList = follows.stream()
                                       .map(follow -> String.valueOf(follow.getFollowId()))
                                       .collect(Collectors.toList());
        // 存入redis
        if (!follows.isEmpty()) {
            for (String followId : followList) {
                template.opsForSet().add(key, followId);
            }
        }
        return followList;
    }

    /**
     * 直接通过redis查询
     */
    @Override
    public List<String> commonFriend(String user, String id) {
        if (StrUtil.isBlank(user) || StrUtil.isBlank(id)) {
            log.warn("找不到关系user：{}，女神{}", user, id);
            return new ArrayList<>();
        }

        String userKey = MyUtil.getKey(Const.FOLLOW_KEY, user);
        String idKey = MyUtil.getKey(Const.FOLLOW_KEY, id);

        Set<String> intersect = template.opsForSet().intersect(userKey, idKey);

        if (intersect == null)
            return new ArrayList<>();
        return new ArrayList<>(intersect);
    }
}
