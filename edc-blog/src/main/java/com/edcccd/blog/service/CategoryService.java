package com.edcccd.blog.service;

import com.edcccd.blog.dto.DtCategory;
import com.edcccd.blog.entity.Category;

import java.util.List;

public interface CategoryService {

    Long count();

    List<DtCategory> listArticleCount();

    List<Category> listAll();

    boolean isExist(String categoryName);

    void create(Category category);

    /**
     * 查询文章的分类
     *
     * @param articleId 主键
     */
    String getByArticleId(Long articleId);

    /**
     * 增加分类
     */
    void insert(Category category);
    /**
     * 修改分类
     */
    void updateById(Category category);
}
