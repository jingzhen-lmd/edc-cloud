package com.edcccd.blog.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.edcccd.blog.dto.DtTalk;
import com.edcccd.blog.entity.UserDetail;
import com.edcccd.blog.service.TalkService;
import com.edcccd.blog.util.UserThreadLocal;
import com.edcccd.common.util.Result;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
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
    @GetMapping("query")
    public Result<List<DtTalk>> query(Long userId, Integer n) {
        List<DtTalk> dtTalks = service.query(userId, n);
        return Result.success(dtTalks);
    }

    /**
     * 查看指定id
     *
     * @param id 说说id
     * @return 单条数据
     */
    @GetMapping("{id}")
    public Result<DtTalk> getById(@PathVariable("id") Long id) {
        DtTalk dtTalk = service.getById(id);
        return Result.success(dtTalk);
    }

    /**
     * 分页查询
     *
     * @param userId 用户id，不一定是作者
     * @return 单条数据
     */
    @GetMapping("page")
    public Result<PageDTO<DtTalk>> page(Long userId) {
        PageDTO<DtTalk> page = service.page(userId);
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

    /**
     * 查询该用户的说说点赞列表
     */
    @GetMapping("likeList")
    public Result<List<Long>> likeList() {
        UserDetail userDetail = UserThreadLocal.getUser();
        if (BeanUtil.isEmpty(userDetail)) {
            return Result.success(new ArrayList<>());
        }

        List<Long> list = service.likeList(userDetail.getUser().getId());
        return Result.success(list);
    }


}
