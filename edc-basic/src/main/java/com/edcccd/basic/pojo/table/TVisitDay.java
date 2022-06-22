package com.edcccd.basic.pojo.table;


import com.baomidou.mybatisplus.annotation.TableName;
import com.edcccd.basic.pojo.BaseTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 访视日期
 */
@TableName("t_visit_day")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TVisitDay extends BaseTable {
    /**
     * 访视期
     */
    private Date visitDate;
}
