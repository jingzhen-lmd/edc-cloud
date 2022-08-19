package com.edcccd.blog.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.edcccd.blog.dto.DtTalk;
import com.edcccd.blog.entity.Talk;
import com.edcccd.blog.service.TalkService;
import com.edcccd.common.util.Result;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (talk)表控制层
 *
 * @author xxxxx
 */
@RestController
@RequestMapping("/talk")
@Api(tags = "说说方法")
public class TalkController {
    /**
     * 服务对象
     */
    @Resource
    private TalkService service;

    /**
     * 查看最新的n条
     *
     * @param userId 用户id，不一定是作者
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Result<List<DtTalk>> query(Long userId, Integer n) {
        List<DtTalk> dtTalks = service.query(userId, n);
        return Result.success(dtTalks);
    }

    /**
     * 分页查询
     *
     * @param userId 用户id，不一定是作者
     * @return 单条数据
     */
    @GetMapping("page")
    public Result<Page<Talk>> page(Long userId) {
        Page<Talk> page = service.page(userId);
        return Result.success(page);
    }

    /**
     * 按点赞、评论查询
     */
    // @GetMapping("page")
    // public Talk page(TalkFilter filter) {
    //     return service.query(userId);
    // }
    @DeleteMapping
    public void delete(Long talkId) {
        service.delete(talkId);
    }

    @PutMapping
    public void update(Long talkId) {
        service.update(talkId);
    }


}
