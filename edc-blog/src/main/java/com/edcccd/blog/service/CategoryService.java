package com.edcccd.blog.service;

import com.edcccd.blog.dto.DtCategory;
import com.edcccd.blog.entity.Category;

import java.util.List;

public interface CategoryService {

  Long count();

  List<DtCategory> articleCount();

  List<Category> listAll();

  boolean isExist(String categoryName);

  void create(Category category);
}
