package com.edcccd.basic.api.pojo.table;

import com.edcccd.basic.api.pojo.BaseTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 入选排除标准
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TInclusionCriteria extends BaseTable {
    // 是否符合
    private boolean isCheck;
    private Date checkDay;
}
