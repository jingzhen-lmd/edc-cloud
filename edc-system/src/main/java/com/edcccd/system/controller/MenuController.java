package com.edcccd.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.edcccd.system.common.Result;
import com.edcccd.system.mapper.menu.VisitTableTypeMapper;
import com.edcccd.system.mapper.visit.VisitMenuItemMapper;
import com.edcccd.system.pojo.menu.VisitMenu;
import com.edcccd.system.pojo.menu.VisitMenuItem;
import com.edcccd.system.pojo.menu.VisitTableType;
import com.edcccd.system.service.menu.VisitMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用于动态构成菜单
 */
@RestController
@RequestMapping("system/menu")
public class MenuController {

    @Autowired
    VisitMenuService service;
    @Autowired
    VisitMenuItemMapper itemMapper;
    @Autowired
    VisitTableTypeMapper typeMapper;

    /**
     * 查询该项目目录
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

    /**
     * 获取菜单下的子菜单
     */
    @GetMapping("{id}")
    public Result<List<VisitMenuItem>> getMenuItem(@PathVariable("id") String id) {
        QueryWrapper<VisitMenuItem> wrapper = new QueryWrapper<>();
        wrapper.eq("visit_menu_id", id);

        return Result.success(itemMapper.selectList(wrapper));
    }

    /**
     * 更新子菜单
     */
    @PostMapping("item")
    public Result<Void> saveMenuItem(@RequestParam("menuId") String menuId,
                                     @RequestBody List<VisitMenuItem> menuItems) {
        if (menuItems.isEmpty())
            return Result.fail("500", "输入集合不能为空");

        // 删除子菜单
        QueryWrapper<VisitMenuItem> wrapper = new QueryWrapper<>();
        wrapper.eq("visit_menu_id", menuId);
        List<VisitMenuItem> list = itemMapper.selectList(wrapper);
        itemMapper.deleteBatchIds(list);

        for (int i = 0; i < menuItems.size(); i++) {
            VisitMenuItem item = menuItems.get(i);
            item.setSequence(i);
            itemMapper.insert(item);
        }
        return Result.success();
    }

    /**
     * 获取访视表的所有类型
     */
    @GetMapping("tableType")
    public Result<List<VisitTableType>> listTableType() {
        List<VisitTableType> lists = typeMapper.selectList(null);

        return Result.success(lists);
    }


}
