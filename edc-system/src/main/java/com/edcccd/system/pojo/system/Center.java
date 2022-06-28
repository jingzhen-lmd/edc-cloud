package com.edcccd.system.pojo.system;

import com.edcccd.system.pojo.BaseTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 中心信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Center extends BaseTable {
    private String name;
    private String code;
    private String address;
    private String phone;
}
