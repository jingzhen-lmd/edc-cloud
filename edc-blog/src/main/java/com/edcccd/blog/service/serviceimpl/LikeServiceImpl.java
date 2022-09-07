package com.edcccd.blog.service.serviceimpl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.edcccd.blog.entity.Enjoy;
import com.edcccd.blog.entity.User;
import com.edcccd.blog.mapper.EnjoyMapper;
import com.edcccd.blog.util.Model;
import com.edcccd.blog.service.LikeService;
import com.edcccd.blog.util.UserThreadLocal;
import com.edcccd.common.util.RedisUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.sql.Struct;
import java.util.List;
import java.util.Optional;

import static com.edcccd.blog.util.Constant.BLOG;
import static com.edcccd.blog.util.Constant.ENJOY_COUNT;

@Service
public class LikeServiceImpl implements LikeService {

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private EnjoyMapper mapper;


    @Override
    public void like(Model model, Long id) {
      // 获取用户id
      User user = UserThreadLocal.getUser();

      // 获取点赞列表,查看是否已经点赞
      boolean enjoyed = isEnjoyed(model.getCaption(), id, user.getId());

      String key = BLOG + ENJOY_COUNT + model + ":" + id;
      if (enjoyed) {
        // 已经点赞则删除

        // 文章的赞-1

        return;
      }
      // 点赞文章
//      redisUtil.inc

      // 文章的赞+1


      // 用户集合加上文章

    }

    private boolean isEnjoyed(String modelName, Long id, Long userId) {
      LambdaQueryWrapper<Enjoy> wrapper = new LambdaQueryWrapper<>();
      wrapper.eq(Enjoy::getUserId, userId);
      wrapper.eq(Enjoy::getModel, modelName);
      Enjoy enjoy = mapper.selectOne(wrapper);

      if (BeanUtil.isEmpty(enjoy)) {
        return false;
      }

      String enjoyContents = enjoy.getEnjoyContents();
      List<String> contents = StrUtil.split(enjoyContents, ",");
      Optional<Long> isEnjoy = contents.stream()
          .map(Long::new)
          .filter(content -> content.equals(id))
          .findAny();
      return isEnjoy.isPresent();
    }

    @Override
    public Integer likeCount(Model model, Long id) {
        String likeCount = redisUtil.getString(BLOG + model.name() + ":" + id);
        return StrUtil.isNotBlank(likeCount) ? Integer.parseInt(likeCount) : 0;
    }
}
