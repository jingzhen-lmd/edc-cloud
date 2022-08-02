package com.edcccd.account.service.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.edcccd.account.api.entity.Role;
import com.edcccd.account.service.mapper.RoleMapper;
import com.edcccd.account.service.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}

