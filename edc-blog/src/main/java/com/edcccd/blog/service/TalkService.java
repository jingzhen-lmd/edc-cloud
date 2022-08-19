package com.edcccd.blog.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.edcccd.blog.dto.DtTalk;
import com.edcccd.blog.entity.Talk;

import java.util.List;

public interface TalkService {

    List<DtTalk> query(Long userId, Integer n);

    Page<Talk> page(Long userId);

    void delete(Long talkId);

    void update(Long talkId);
}
