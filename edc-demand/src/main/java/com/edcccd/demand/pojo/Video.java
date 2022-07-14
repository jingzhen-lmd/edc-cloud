package com.edcccd.demand.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 视频作品
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Video {
    private Long id;
    private String name;
    private String data;
    // 点赞数目
    private long fansNum;
}
