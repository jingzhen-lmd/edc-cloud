package com.edcccd.basic.pojo.table;

import com.baomidou.mybatisplus.annotation.TableName;
import com.edcccd.basic.pojo.BaseTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 过敏史
 */
@TableName("t_allergic_history")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TAllergicHistory extends BaseTable {
    private String isAllergicHistory;
    // 如果有，粗略说明
    private String note;
}
