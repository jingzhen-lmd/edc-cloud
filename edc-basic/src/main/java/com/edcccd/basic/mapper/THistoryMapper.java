package com.edcccd.basic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.edcccd.basic.pojo.table.TFamilyHistory;
import com.edcccd.basic.pojo.table.THistory;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface THistoryMapper extends BaseMapper<THistory> {
}
