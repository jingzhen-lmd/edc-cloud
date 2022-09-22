package com.edcccd.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtEnjoy {
    private Long id;

    private Long userId;

    /**
     * 内容模块
     */
    private String model;

    /**
     * 喜欢内容的id
     */
    private List<Long> enjoyContents = new ArrayList<>();
}
