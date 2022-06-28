package com.edcccd.basic.api.pojo.table;

import com.edcccd.basic.api.pojo.BaseTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 凝血功能明细
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TCoagulationItem extends BaseTable {
    private String parentId;
    // 编号
    private String num;
    // 检查项目
    private String checkName;
    // 结果
    private String result;
    // 评估
    private String assess;
    // 病史编号
    private String historyNum;
    // 相关不良事件编号
    private String adverseEventNum;
    // 单位
    private String unit;
    // 正常值范围
    private String range;
}
