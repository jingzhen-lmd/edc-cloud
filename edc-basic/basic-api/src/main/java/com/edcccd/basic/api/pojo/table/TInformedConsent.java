package com.edcccd.basic.api.pojo.table;

import com.baomidou.mybatisplus.annotation.TableName;
import com.edcccd.basic.api.pojo.BaseTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 预筛表单:
 * 知情同意表
 */
@TableName("t_informed_consent")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TInformedConsent extends BaseTable {
    /**
     * 是否同意
     */
    private boolean isAgree;
    /**
     * 签署日期
     */
    private Date logDate;
    /**
     * 方案版本
     */
    private String plainVersion;
    /**
     * 方案日期
     */
    private Date plainDate;
}
