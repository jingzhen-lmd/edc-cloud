package com.edcccd.system.pojo.menu;

import com.edcccd.system.pojo.BaseTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 项目访视期，实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Visit extends BaseTable {
    private long testerId;
    private long visitMenuId;
    private String name;
    private String state;
}
