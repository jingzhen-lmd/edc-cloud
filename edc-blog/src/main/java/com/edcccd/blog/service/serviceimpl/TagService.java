package com.edcccd.blog.service.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.edcccd.blog.entity.Tag;
import com.edcccd.blog.mapper.TagMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TagService {

  @Resource
  TagMapper mapper;

  /**
   * 获取指定数量的标签
   */
  public List<Tag> query(int num) {
    QueryWrapper<Tag> wrapper = new QueryWrapper<>();
    wrapper.last("limit" + num);
    return mapper.selectList(wrapper);
  }

  /**
   * 查询文章的所有标签
   *
   * @param articleId
   *     文章id
   */
  public List<Tag> queryByArticle(Long articleId) {
    return mapper.queryByArticle(articleId);
  }


  public Tag getById(Long id) {
    return mapper.selectById(id);
  }
}
