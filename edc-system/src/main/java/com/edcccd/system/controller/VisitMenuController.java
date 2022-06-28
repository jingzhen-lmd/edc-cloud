package com.edcccd.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.edcccd.system.common.Result;
import com.edcccd.system.pojo.menu.VisitMenu;
import com.edcccd.system.service.menu.VisitMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 菜单列
 */
@RestController
@RequestMapping("system/visitMenu")
public class VisitMenuController {

    @Autowired
    VisitMenuService service;

    @GetMapping("{id}")
    public VisitMenu get(@PathVariable("id") String id) {
        QueryWrapper<VisitMenu> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id);

        return service.getOne(wrapper);
    }

    /**
     * 通过基本信息查询访视期目录
     *
     * @return 访视期目录
     */
    @GetMapping
    public Result<List<VisitMenu>> getMenu(@RequestParam("projectId") String projectId,
                                           @RequestParam("centerId") String centerId) {
        List<VisitMenu> menu = service.getMenu(projectId, centerId);
        return Result.success(menu);
    }

    /**
     * 更新菜单
     */
    @PostMapping
    public Result<Void> saveMenu(@RequestBody List<VisitMenu> menus) {
        if (menus.isEmpty())
            return Result.fail("500", "输入集合不能为空");
        try {
            service.saveMenu(menus);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.success();
    }


}
