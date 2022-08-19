package com.edcccd.blog.service.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.edcccd.blog.dto.DtTalk;
import com.edcccd.blog.entity.Talk;
import com.edcccd.blog.mapper.TalkMapper;
import com.edcccd.blog.service.TalkService;
import com.edcccd.blog.util.PageUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class TalkServiceImpl implements TalkService {

    @Resource
    private TalkMapper mapper;

    @Override
    public List<DtTalk> query(Long userId, Integer n) {
        //查询说说
        LambdaQueryWrapper<Talk> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Talk::getUserId, userId);
        wrapper.orderByDesc(Talk::getCreateTime);
        wrapper.last("limit " + n);
        List<Talk> talks = mapper.selectList(wrapper);

        List<DtTalk> dtTalks = new ArrayList<>();
        // todo 在查询前端的参数，放进去
        for (Talk talk : talks) {
            DtTalk dtTalk = new DtTalk(talk);
            // 图片、点赞、评论
        }

        return dtTalks;
    }

    @Override
    public Page<Talk> page(@RequestParam("userId") Long userId) {
        LambdaQueryWrapper<Talk> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Talk::getUserId, userId);
        Page<Talk> page = PageUtils.getPage(Talk.class);

        mapper.selectPage(page, wrapper);
        return page;
    }

    @Override
    public void delete(Long talkId) {
        // todo 没实现
        throw new RuntimeException("功能为实现");
    }

    @Override
    public void update(Long talkId) {
        // todo 没实现
        throw new RuntimeException("功能为实现");
    }
}
