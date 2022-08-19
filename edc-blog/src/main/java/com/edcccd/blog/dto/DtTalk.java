package com.edcccd.blog.dto;

import cn.hutool.core.bean.BeanUtil;
import com.edcccd.blog.entity.Talk;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * 说说，相当于发朋友圈，不正式的文章
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtTalk {
    private Long id;
    /**
     * 文本内容
     */
    private String txt;
    /**
     * 封面
     */
    private String picture;
    /**
     * 附图
     */
    private List<String> images;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 是否置顶
     */
    private String isTop;
    /**
     * 点赞量
     */
    private Integer likeCount;
    /**
     * 评论量
     */
    private Integer commentCount;

    private Long creatorId;
    private String creatorName;
    private Date createTime;
    /**
     * 此文章权限
     */
    private String power;
    /**
     * 删除=1
     */
    private String deleted;

    public DtTalk(Talk talk) {
        BeanUtil.copyProperties(talk, this);
    }
}