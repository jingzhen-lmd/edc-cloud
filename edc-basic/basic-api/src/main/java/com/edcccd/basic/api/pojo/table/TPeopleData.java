package com.edcccd.basic.api.pojo.table;

import com.baomidou.mybatisplus.annotation.TableName;
import com.edcccd.basic.api.pojo.BaseTable;
import com.edcccd.basic.api.pojo.entity.AgeLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 人员资料表
 */
@TableName("t_people_data")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TPeopleData extends BaseTable {
    private Date birthday;
    private int age;
    private AgeLevel ageLevel;
    private String sex;
    private String race;
}
