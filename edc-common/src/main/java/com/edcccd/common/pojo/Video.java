package com.edcccd.common.pojo;

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
    private long id;
    private String name;
    private String data;
}
