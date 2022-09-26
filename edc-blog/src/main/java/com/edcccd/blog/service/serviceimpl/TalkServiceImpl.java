package com.edcccd.blog.service.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.edcccd.blog.dto.DtTalk;
import com.edcccd.blog.entity.Talk;
import com.edcccd.blog.mapper.TalkMapper;
import com.edcccd.blog.service.LikeService;
import com.edcccd.blog.service.TalkService;
import com.edcccd.blog.util.Model;
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
    @Resource
    private LikeServiceImpl startService;

    @Resource
    LikeService likeService;

    @Override
    public List<DtTalk> query(Long userId, Integer n) {
        //查询说说
        LambdaQueryWrapper<Talk> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Talk::getUserId, userId);
        wrapper.orderByDesc(Talk::getCreateTime);
        wrapper.last("limit " + n);
        List<Talk> talks = mapper.selectList(wrapper);

        return addDetail(talks);
    }

    @Override
    public PageDTO<DtTalk> page(@RequestParam("userId") Long userId) {
        LambdaQueryWrapper<Talk> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Talk::getUserId, userId);
        Page<Talk> page = PageUtils.getPage(Talk.class);
        mapper.selectPage(page, wrapper);

        PageDTO<DtTalk> pageDTO = new PageDTO<>(page.getCurrent(), page.getSize());
        List<DtTalk> dtTalks = addDetail(page.getRecords());
        pageDTO.setRecords(dtTalks);

        return pageDTO;
    }

    private List<DtTalk> addDetail(List<Talk> talks) {
        List<DtTalk> dtTalks = new ArrayList<>();
        // todo 在查询前端的参数，放进去
        for (Talk talk : talks) {
            DtTalk dtTalk = new DtTalk(talk);
            dtTalks.add(dtTalk);
            // 查询点赞
            dtTalk.setLikeCount(startService.likeCount(Model.TALK, talk.getId()));
            // 图片、评论
        }
        return dtTalks;
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

    @Override
    public DtTalk getById(Long id) {
        Talk talk = mapper.selectById(id);
        DtTalk dtTalk = new DtTalk(talk);

        return dtTalk;
    }

    @Override
    public List<Long> likeList(Long userId) {
        if (userId == null) {
            return new ArrayList<>();
        }

        List<Long> list = likeService.queryUserLike(Model.TALK, userId);
        return list;
    }
}
