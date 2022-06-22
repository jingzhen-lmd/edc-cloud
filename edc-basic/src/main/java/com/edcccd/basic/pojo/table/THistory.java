package com.edcccd.basic.pojo.table;


import com.baomidou.mybatisplus.annotation.TableName;
import com.edcccd.basic.pojo.BaseTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 既往史
 */
@TableName("t_history")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class THistory extends BaseTable {
    //    是否有既往史
    private boolean haveHistory;
}
