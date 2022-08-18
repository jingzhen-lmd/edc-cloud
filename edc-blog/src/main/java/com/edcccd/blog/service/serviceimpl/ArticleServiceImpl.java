package com.edcccd.blog.service.serviceimpl;

import com.edcccd.blog.dto.DtArticle;
import com.edcccd.blog.entity.Article;
import com.edcccd.blog.entity.Tag;
import com.edcccd.blog.mapper.ArticleMapper;
import com.edcccd.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

  @Autowired
  ArticleMapper mapper;
  @Autowired
  TagService tagService;

  @Override
  public DtArticle getById(Long id) {
    Article article = mapper.selectById(id);
    if (article == null)
      return null;

    List<Tag> tags = tagService.queryByArticle(article.getId());

    DtArticle dtArticle = new DtArticle(article);
    dtArticle.setTags(tags);
    return dtArticle;
  }

  @Override
  public List<Article> list() {
    // todo 改成分页查询，临时随便查询
    return mapper.selectList(null);
  }

  @Override
  public void save(DtArticle article) {
    throw new RuntimeException("暂时没有实现");
  }

  @Override
  public void removeById(Long articleId) {
    // todo 同样需要删除标签
    throw new RuntimeException("暂时没有实现");
  }

  @Override
  public void updateById(DtArticle article) {
    throw new RuntimeException("暂时没有实现");
  }
}

