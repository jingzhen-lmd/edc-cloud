package com.edcccd.account.service.controller;

import com.edcccd.account.api.entity.User;
import com.edcccd.account.service.service.serviceImpl.UserServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (user)表控制层
 *
 * @author xxxxx
 */
@RestController
@RequestMapping("/user")
public class UserController {
    /**
     * 服务对象
     */
    @Resource
    private UserServiceImpl userServiceImpl;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public User selectOne(Integer id) {
        return userServiceImpl.getById(id);
    }

    /**
     * 创建新用户
     *
     * @param user 主键
     * @return 单条数据
     */
    @PostMapping("create")
    public boolean create(@RequestBody User user) {
        // todo 待完善
        return userServiceImpl.save(user);
    }


}
