package com.edcccd.blog.service.serviceimpl;

import com.edcccd.blog.dto.DtArticle;
import com.edcccd.blog.entity.Article;
import com.edcccd.blog.entity.ArticleBody;
import com.edcccd.blog.entity.Tag;
import com.edcccd.blog.mapper.ArticleMapper;
import com.edcccd.blog.service.ArticleBodyService;
import com.edcccd.blog.service.ArticleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Resource
    ArticleMapper mapper;
    @Resource
    ArticleBodyService bodyService;
    @Resource
    TagServiceImpl tagServiceImpl;

    @Override
    public DtArticle getById(Long id) {
        Article article = mapper.selectById(id);
        if (article == null)
            return null;

        List<Tag> tags = tagServiceImpl.queryByArticle(article.getId());
        ArticleBody articleBody = bodyService.getByArticleId(article.getId());

        DtArticle dtArticle = new DtArticle(article);
        dtArticle.setTags(tags);
        dtArticle.setArticleBody(articleBody);
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
    public void removeById(Long id) {
        Article article = mapper.selectById(id);
        if (article == null)
            return;

        mapper.deleteById(id);
        bodyService.removeByArticleId(id);
        tagServiceImpl.removeByArticleId(id);
    }

    @Override
    public void updateById(DtArticle article) {
        throw new RuntimeException("暂时没有实现");
    }
}

