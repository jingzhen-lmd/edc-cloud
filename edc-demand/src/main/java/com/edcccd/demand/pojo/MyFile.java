package com.edcccd.demand.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyFile {
    private Integer id;
    private String name;
    private boolean directory;
    private Long uploadTime;
    // 文件所在文件夹，根目录算起
    private String local;
    // 单位kb
    private Long size;
}
