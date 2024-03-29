package com.edcccd.account.service.controller;

import com.edcccd.account.api.entity.Role;
import com.edcccd.account.service.service.serviceImpl.RoleServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (role)表控制层
 *
 * @author xxxxx
 */
@RestController
@RequestMapping("/role")
public class RoleController {
    /**
     * 服务对象
     */
    @Resource
    private RoleServiceImpl roleServiceImpl;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Role selectOne(Integer id) {
        return roleServiceImpl.getById(id);
    }

}
