package com.edcccd.gateway.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.edcccd.account.api.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    /**
     * 查询用户的权限
     *
     * @param userId 用户id
     */
    List<String> listUserPower(Long userId);
}