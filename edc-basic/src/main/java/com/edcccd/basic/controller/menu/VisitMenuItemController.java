//package com.edcccd.basic.controller.menu;
//
//import com.edcccd.basic.common.Result;
//import com.edcccd.system.pojo.menu.VisitMenuItem;
//import com.edcccd.basic.service.menu.VisitMenuItemService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
///**
// * 菜单明细列，对应子表
// */
//@RestController
//public class VisitMenuItemController {
//
//    @Autowired
//    VisitMenuItemService service;
//
//    /**
//     * 根据uuid查询明细
//     */
//    public void getVisitMenuItem() {
//
//    }
//
//    /**
//     * 查询主菜单下所有明细
//     */
//    @GetMapping("visitMenuItem")
//    public Result<List<VisitMenuItem>> listVisitMenuItem(@RequestParam("visitMenuUuid") String visitMenuUuid) {
////        if (MyUtils.isNull(visitMenuUuid))
////            return Result.fail_null(visitMenuUuid);
//
//        List<VisitMenuItem> list = service.listVisitMenuItem(visitMenuUuid);
//        return Result.success(list);
//    }
//
//    /**
//     * 保存主菜单下所有明细
//     *
//     * @param menuItemData 主菜单uuid+菜单明细
//     */
//    @PostMapping("visitMenuItem")
//    public Result<Void> save(@RequestBody MenuItemData menuItemData) throws Exception {
//        String visitMenuUuid = menuItemData.getVisitMenuUuid();
//        List<VisitMenuItem> visitMenuItem = menuItemData.getVisitMenuItem();
//
////        if (MyUtils.isNull(visitMenuUuid) || MyUtils.checkNull(visitMenuItem))
////            return Result.fail_null(visitMenuUuid);
//
//        service.saveItems(visitMenuUuid, visitMenuItem);
//
//        return Result.success();
//    }
//
//}
