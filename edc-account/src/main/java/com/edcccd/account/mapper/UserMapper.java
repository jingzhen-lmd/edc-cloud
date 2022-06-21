package com.edcccd.account.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.edcccd.account.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
