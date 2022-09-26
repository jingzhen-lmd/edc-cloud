package com.edcccd.blog.service.serviceimpl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.edcccd.blog.dto.DtComment;
import com.edcccd.blog.entity.Comment;
import com.edcccd.blog.mapper.CommentMapper;
import com.edcccd.blog.service.CommentService;
import com.edcccd.blog.util.Model;
import com.edcccd.blog.util.PageUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Resource
    CommentMapper mapper;

    @Override
    public List<DtComment> getByTargetId(Model model, Long id) {
        LambdaQueryWrapper<Comment> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Comment::getModel, model.name());
        wrapper.eq(Comment::getTargetId, id);
        List<Comment> comments = mapper.selectList(wrapper);


        List<DtComment> list = BeanUtil.copyToList(comments, DtComment.class);

        // 排序，分类
        list.stream()
                .filter(item -> item.getPid() != null)
                .forEach(item -> {
                    for (DtComment comment : list) {
                        if (comment.getId().equals(item.getPid())) {
                            comment.getItems().add(item);
                        }
                    }
                });
        return list;
    }

    @Override
    public PageDTO<Comment> page(String type) {
        Page<Comment> page = PageUtils.getPage(Comment.class);
        LambdaQueryWrapper<Comment> wrapper = new LambdaQueryWrapper<>();
        wrapper.isNull(Comment::getPid);
        wrapper.eq(Comment::getModel, type);
        mapper.selectPage(page, wrapper);

        PageDTO<Comment> pageDTO = new PageDTO<>(page.getCurrent(), page.getSize());
        pageDTO.setRecords(page.getRecords());
        return pageDTO;
    }

    @Override
    public void saveComment(Comment comment) {

    }

    @Override
    public void saveCommentLike(Integer commentId) {

    }
}
