package com.edcccd.blog.service.serviceimpl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.edcccd.blog.dto.DtEnjoy;
import com.edcccd.blog.entity.Enjoy;
import com.edcccd.blog.entity.UserDetail;
import com.edcccd.blog.mapper.EnjoyMapper;
import com.edcccd.blog.service.LikeService;
import com.edcccd.blog.util.Model;
import com.edcccd.blog.util.UserThreadLocal;
import com.edcccd.common.util.RedisUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.edcccd.blog.util.Constant.ENJOY_COUNT;

@Service
public class LikeServiceImpl implements LikeService {
    @Resource
    private RedisUtil redisUtil;

    @Resource
    private EnjoyMapper mapper;

    @Override
    public Long like(Model model, Long id) {
        UserDetail userDetail = UserThreadLocal.getUser();
        if (BeanUtil.isEmpty(userDetail)) {
            return null;
        }

        // 获取点赞列表,查看是否已经点赞
        DtEnjoy dtEnjoy = getEnjoyed(model.getCaption(), userDetail.getUser().getId());
        String key = ENJOY_COUNT + model + ":" + id;
        // 已经点赞
        if (dtEnjoy.getEnjoyContents().contains(id)) {
            // 用户喜欢列表删除
            dtEnjoy.getEnjoyContents().remove(id);
            mapper.updateById(transEnjoy(dtEnjoy));
            // 文章的赞-1
            return redisUtil.decrement(key);
        }
        // 用户集合加上文章
        dtEnjoy.getEnjoyContents().add(id);
        mapper.updateById(transEnjoy(dtEnjoy));
        // 文章的赞+1
        return redisUtil.increment(key);
    }

    // 用户点赞数在数据库中保存
    private DtEnjoy getEnjoyed(String modelName, Long userId) {
        DtEnjoy dtEnjoy = new DtEnjoy();
        dtEnjoy.setModel(modelName);
        dtEnjoy.setUserId(userId);

        LambdaQueryWrapper<Enjoy> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Enjoy::getUserId, userId);
        wrapper.eq(Enjoy::getModel, modelName);
        Enjoy enjoy = mapper.selectOne(wrapper);

        // 为空则创建
        if (BeanUtil.isEmpty(enjoy)) {
            enjoy = transEnjoy(dtEnjoy);
            mapper.insert(enjoy);
        }

        // 不为空则返回转化后的DtEnjoy
        return transEnjoy(enjoy);
    }

    public Enjoy transEnjoy(DtEnjoy dtEnjoy) {
        Enjoy enjoy = new Enjoy();
        enjoy.setId(dtEnjoy.getId());
        enjoy.setModel(dtEnjoy.getModel());
        enjoy.setUserId(dtEnjoy.getUserId());

        StringBuilder sb = new StringBuilder();
        dtEnjoy.getEnjoyContents().forEach((x) -> sb.append(x).append(","));
        enjoy.setEnjoyContents(sb.toString());
        return enjoy;
    }

    public DtEnjoy transEnjoy(Enjoy enjoy) {
        DtEnjoy dtEnjoy = new DtEnjoy();
        dtEnjoy.setId(enjoy.getId());
        dtEnjoy.setModel(enjoy.getModel());
        dtEnjoy.setUserId(enjoy.getUserId());

        String enjoyContents = enjoy.getEnjoyContents();
        if (StrUtil.isBlank(enjoyContents)) {
            dtEnjoy.setEnjoyContents(new ArrayList<>());
            return dtEnjoy;
        }

        List<Long> contents = StrUtil.split(enjoyContents, ",", true, true)
                .stream()
                .map(Long::new)
                .collect(Collectors.toList());
        dtEnjoy.setEnjoyContents(contents);
        return dtEnjoy;
    }

    @Override
    public Long likeCount(Model model, Long id) {
        String likeCount = redisUtil.getString(ENJOY_COUNT + model + ":" + id);
        return StrUtil.isNotBlank(likeCount) ? Long.parseLong(likeCount) : 0;
    }

    @Override
    public List<Long> queryUserLike(Model model, Long userId) {
        LambdaQueryWrapper<Enjoy> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Enjoy::getUserId, userId);
        wrapper.eq(Enjoy::getModel, model.getCaption());

        Enjoy enjoys = mapper.selectOne(wrapper);
        DtEnjoy dtEnjoy = transEnjoy(enjoys);

        return dtEnjoy.getEnjoyContents();
    }
}
