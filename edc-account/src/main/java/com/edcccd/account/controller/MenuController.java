package com.edcccd.account.controller;

import com.edcccd.account.entity.Menu;
import com.edcccd.account.service.serviceImpl.MenuServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (menu)表控制层
 *
 * @author xxxxx
 */
@RestController
@RequestMapping("/menu")
public class MenuController {
    /**
     * 服务对象
     */
    @Resource
    private MenuServiceImpl menuServiceImpl;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Menu selectOne(Integer id) {
        return menuServiceImpl.getById(id);
    }

}
