//package com.edcccd.basic.basic.controller.menu;
//
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.edcccd.basic.basic.common.Result;
//import com.edcccd.system.pojo.menu.VisitMenu;
//import com.edcccd.basic.basic.service.menu.VisitMenuService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
///**
// * 菜单列
// */
//@RestController
//public class VisitMenuController {
//
//    @Autowired
//    VisitMenuService service;
//
//    @GetMapping("visitMenu")
//    public VisitMenu get(@RequestParam String uuid) {
//
//        QueryWrapper<VisitMenu> wrapper = new QueryWrapper<>();
//        wrapper.eq("uuid", uuid);
//        VisitMenu one = service.getOne(wrapper);
//
//        return one;
//    }
//
//    /**
//     * 通过基本信息查询访视期目录
//     *
//     * @param projectUuid 项目uuid
//     * @param centerUuid  中心uuid
//     * @return 访视期目录
//     */
//    @GetMapping("visitMenu/basicInfo")
//    public Result<List<VisitMenu>> getMenu(@RequestParam("projectUuid") String projectUuid,
//                                           @RequestParam("centerUuid") String centerUuid) {
//
//        List<VisitMenu> menu = service.getMenu(projectUuid, centerUuid);
//        return Result.success(menu);
//    }
//
//    /**
//     * 更新菜单
//     */
//    @PostMapping("visitmenu")
//    public Result<Void> saveMenu(@RequestBody List<VisitMenu> menus) {
//        if (menus.isEmpty())
//            return Result.fail("500", "输入集合不能为空");
//
//        try {
//            service.saveMenu(menus);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//        return Result.success();
//    }
//
//    /**
//     * 更新菜单,测试
//     */
//    @PostMapping("visitmenu/test")
//    public Result<Void> test(@RequestBody VisitMenu menus) {
//
////        QueryWrapper<VisitMenu> wrapper = new QueryWrapper<>();
////        wrapper.eq("projectUuid", projectUuid);
////        wrapper.eq("centerUuid", centerUuid);
////        List<VisitMenu> list = service.list(wrapper);
//
//        return Result.success();
//    }
//
//}
