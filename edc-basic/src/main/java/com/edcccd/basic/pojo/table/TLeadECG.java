package com.edcccd.basic.pojo.table;

import com.edcccd.basic.pojo.BaseTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 12-导联心电图
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TLeadECG extends BaseTable {
    // 是否检查
    private boolean isCheck;
    private Date checkDay;

    private String result;
    // 如果异常，请描述
    private String description;
}
