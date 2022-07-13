package com.edcccd.demand.service.serviceImpl;

import com.edcccd.demand.command.Const;
import com.edcccd.demand.service.ActiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

import static com.edcccd.demand.command.Const.ACTIVE_USER;

@Service
public class ActiveServiceImpl implements ActiveService {

    @Autowired
    StringRedisTemplate template;

    @Override
    public long webActive() {
        Long count = template
                .execute((RedisCallback<Long>) connection -> connection.bitCount(Const.ACTIVE_WEB.getBytes()));
        return count == null ? 0 : count;
    }

    @Override
    public void loginActive(String user) {
        //总网站对饮活跃度+1
        template.opsForValue().setBit(Const.ACTIVE_WEB, Long.parseLong(user), true);
        //该用户当天活跃度+1
        Duration between = Duration.between(com.edcccd.common.util.Const.LOCAL_DATE_TIME, LocalDateTime.now());
        long dayNum = between.toDays();

        String key = ACTIVE_USER + ":" + user;
        template.opsForValue().setBit(key, dayNum, true);
    }

    @Override
    public void logoutActive(String user) {
        //总网站对饮活跃度-1
        template.opsForValue().setBit(Const.ACTIVE_WEB, Long.parseLong(user), false);
    }

    @Override
    public long userActiveCount(String user) {
        String key = ACTIVE_USER + ":" + user;
        Long count = template.execute((RedisCallback<Long>) connection -> connection.bitCount(key.getBytes()));

        return count == null ? 0 : count;
    }
}
