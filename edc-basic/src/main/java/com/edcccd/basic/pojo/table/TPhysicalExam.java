package com.edcccd.basic.pojo.table;

import com.edcccd.basic.pojo.BaseTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 体格检查
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TPhysicalExam extends BaseTable {
    private boolean isCheck;
    private Date checkDay;
}
