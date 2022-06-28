package com.edcccd.system.pojo.menu;

import com.edcccd.system.pojo.BaseTable;
import lombok.Data;

/**
 * 项目访视期目录类
 */
@Data
public class VisitMenu extends BaseTable {
    private String projectId;
    private String centerId;
    private String menuName;
    /**
     * 菜单顺序
     */
    private String sequence;
}
