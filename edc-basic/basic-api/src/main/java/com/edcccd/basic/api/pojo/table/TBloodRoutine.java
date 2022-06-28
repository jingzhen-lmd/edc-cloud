package com.edcccd.basic.api.pojo.table;

import com.edcccd.basic.api.pojo.BaseTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 血常规
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TBloodRoutine extends BaseTable {
    // 是否检查
    private boolean isCheck;
    // 检查时间，精确到分钟
    private Date checkDay;
}
