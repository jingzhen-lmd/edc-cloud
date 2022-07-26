package com.edcccd.basic.api.pojo.table;


import com.edcccd.basic.api.pojo.BaseTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 访视日期
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TVisitDay extends BaseTable {
    /**
     * 访视期
     */
    private Date visitDate;

    private String tester;
    private String aMenuItem;
}
