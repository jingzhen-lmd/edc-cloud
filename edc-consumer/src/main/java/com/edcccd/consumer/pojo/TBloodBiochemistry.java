package com.edcccd.consumer.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 血生化
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TBloodBiochemistry extends BaseTable {
    // 是否检查
    private boolean isCheck;
    // 检查时间，精确到分钟
    private Date checkDay;
}
