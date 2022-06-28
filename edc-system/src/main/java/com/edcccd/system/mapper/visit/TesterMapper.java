package com.edcccd.system.mapper.visit;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.edcccd.system.pojo.Tester;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TesterMapper extends BaseMapper<Tester> {

    @Select("select * from tester where project_id = #{projectId} and center_id = #{center_id}")
    List<Tester> queryByBaseId(String projectId, String centerId);

}
