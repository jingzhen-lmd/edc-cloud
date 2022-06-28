package com.edcccd.basic.api.pojo.table;

import com.edcccd.basic.api.pojo.BaseTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 生命体征
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TVitalSigns extends BaseTable {
    // 是否检查
    private boolean isCheck;
    private Date checkDay;
}
