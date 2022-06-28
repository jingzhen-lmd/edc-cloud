package com.edcccd.system.pojo.menu;

import com.edcccd.system.pojo.BaseTable;
import lombok.Data;

/**
 * 访视期明细
 */
@Data
public class VisitMenuItem extends BaseTable {
    private String visitMenuId;
    private String tableType;
    private String tableName;
    private int sequence;
}
