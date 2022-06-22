package com.edcccd.basic.pojo.table;

import com.edcccd.basic.pojo.BaseTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 入选排除标准明细
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TInclusionCriteriaItem extends BaseTable {
    private String parentId;
    // 编号
    private String num;
    // 标准类型
    private String standardName;
    // 标准编号
    private String standardNum;
}
