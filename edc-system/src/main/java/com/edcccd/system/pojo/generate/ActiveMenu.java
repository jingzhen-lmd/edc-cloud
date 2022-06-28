package com.edcccd.system.pojo.generate;

import com.edcccd.system.pojo.BaseTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 动态菜单对象
 * 返回给前端
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActiveMenu extends BaseTable {
    private int menuType;
    private String tableName;
    private String path;
    private String icon;

    private List<ActiveMenu> children;
}
