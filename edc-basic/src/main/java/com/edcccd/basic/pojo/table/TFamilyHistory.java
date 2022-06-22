package com.edcccd.basic.pojo.table;

import com.baomidou.mybatisplus.annotation.TableName;
import com.edcccd.basic.pojo.BaseTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 家族史
 */
@TableName("t_family_history")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TFamilyHistory extends BaseTable {
    private String isFamilyHistory;
    // 如果有，粗略说明
    private String note;
}
