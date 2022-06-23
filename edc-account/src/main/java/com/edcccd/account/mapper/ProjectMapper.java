package com.edcccd.account.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.edcccd.account.pojo.Project;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProjectMapper extends BaseMapper<Project> {


    @Select("select p.* from user_project u inner join project p on p.id = u.project_id " +
            "where u.user_id=#{userId}")
    List<Project> queryByUser(long userId);
}
