package com.edcccd.basic.api.pojo.table;

import com.edcccd.basic.api.pojo.BaseTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 体格检查明细
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TPhysicalExamItem extends BaseTable {
    private String parentId;
    // 编号
    private String num;
    // 检查部位
    private String checkName;
    // 结果
    private String result;
    // 异常详细信息
    private String abnormalDetail;
    // 病史编号
    private String historyNum;
    // 相关不良事件编号
    private String adverseEventNum;
}
