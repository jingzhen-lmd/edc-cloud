package com.edcccd.basic.pojo.table;


import com.baomidou.mybatisplus.annotation.TableName;
import com.edcccd.basic.pojo.BaseTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 既往史明细
 */
@TableName("t_history_item")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class THistoryItem extends BaseTable {
    private String historyId;
    //    编号
    private String num;
    private String name;
    private Date startDay;
    private boolean isContinue;
    private Date endDay;
    //    是否正在治疗
    private boolean isTreatment;
}
