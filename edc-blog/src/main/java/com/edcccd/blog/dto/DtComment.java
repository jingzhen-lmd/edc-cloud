package com.edcccd.blog.dto;

import com.edcccd.blog.util.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 评论
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtComment {
    private Long id;
    /**
     * 父评论id
     */
    private Long pid;

    /**
     * 对象的类型
     */
    private Model model;

    private String targetId;

    private Long userId;

    /**
     * 评论内容
     */
    private String txt;

    private Long creatorId;
    private String creatorName;
    private Date createTime;

    /**
     * 第几层
     */
    private Integer layer;

    private String deleted;

    // 子评论
    private List<DtComment> items = new ArrayList<>();
}