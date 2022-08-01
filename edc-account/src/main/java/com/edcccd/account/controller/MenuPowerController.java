package com.edcccd.account.controller;

import com.edcccd.account.entity.MenuPower;
import com.edcccd.account.service.serviceImpl.MenuPowerServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (menu_power)表控制层
 *
 * @author xxxxx
 */
@RestController
@RequestMapping("/menu_power")
public class MenuPowerController {
    /**
     * 服务对象
     */
    @Resource
    private MenuPowerServiceImpl menuPowerServiceImpl;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public MenuPower selectOne(Integer id) {
        return menuPowerServiceImpl.getBaseMapper()
                                   .selectById(id);
    }

}
