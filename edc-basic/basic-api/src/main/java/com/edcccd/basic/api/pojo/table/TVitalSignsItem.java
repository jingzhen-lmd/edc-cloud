package com.edcccd.basic.api.pojo.table;

import com.edcccd.basic.api.pojo.BaseTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 生命体征明细
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TVitalSignsItem extends BaseTable {
    // 编号
    private String num;
    // 检查项
    private String checkName;
    // 结果
    private String result;
    // 单位
    private String unit;
    // 评估
    private String assess;
}
