package com.edcccd.blog.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.edcccd.blog.dto.DtTalk;

import java.util.List;

public interface TalkService {

    List<DtTalk> query(Long userId, Integer n);

    PageDTO<DtTalk> page(Long userId);

    void delete(Long talkId);

    void update(Long talkId);

    DtTalk getById(Long id);

    List<Long> likeList(Long userId );
}
