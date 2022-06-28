package com.edcccd.system.pojo.menu;

import com.edcccd.system.pojo.BaseTable;
import lombok.Data;

/**
 * 访视表类型
 */
@Data
public class VisitTableType extends BaseTable {
    private String name;
    /**
     * 数据库中表名
     */
    private String tableName;
}
