package com.edcccd.basic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.edcccd.basic.pojo.table.TLeadECG;
import com.edcccd.basic.pojo.table.TPeopleData;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TPeopleDataMapper extends BaseMapper<TPeopleData> {
}
