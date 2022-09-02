package com.edcccd.blog.service.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.edcccd.blog.dto.DtCategory;
import com.edcccd.blog.entity.Category;
import com.edcccd.blog.mapper.CategoryMapper;
import com.edcccd.blog.service.CategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {

  @Resource
  CategoryMapper mapper;

  @Override
  public Long count() {
    return mapper.selectCount(null);
  }

  @Override
  public List<DtCategory> articleCount() {
    return mapper.articleCount();
  }

  @Override
  public List<Category> listAll() {
    return mapper.selectList(null);
  }

  @Override
  public boolean isExist(String categoryName) {
    LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
    wrapper.eq(Category::getName, categoryName);
    Category category = mapper.selectOne(wrapper);
    return category != null;
  }

  @Override
  public void create(Category category) {
    mapper.insert(category);
  }

}
