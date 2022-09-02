package com.edcccd.blog.controller;

import com.edcccd.blog.service.CategoryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (tag)表控制层
 *
 * @author xxxxx
 */
@RestController
@RequestMapping("/category")
public class CategoryController {
  @Resource
  private CategoryService service;


}
