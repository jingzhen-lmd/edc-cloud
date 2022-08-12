package com.edcccd.system.entity;

import lombok.Data;

/**
 * 用于查需菜单栏
 */
@Data
public class MenuFilter {
    /**
     * 菜单用于哪个项目
     */
    private String projectId;

    /**
     * 菜单用于哪个部分
     */
    private String partId;

}
