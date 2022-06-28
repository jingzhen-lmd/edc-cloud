//package com.edcccd.system.pojo.generate;
//
//import com.edcccd.system.pojo.menu.VisitMenu;
//
//import java.util.ArrayList;
//import java.util.Map;
//import java.util.Set;
//
///**
// * 菜单工厂
// * 用于创建工厂
// */
//public class MenuFactory {
//    public ActiveMenu createMenu(String menuName) {
//        ActiveMenu activeMenu = new ActiveMenu();
////        activeMenu.setId(MyUtils.genUuid());
//        activeMenu.setTableName(menuName);
//        activeMenu.setMenuType(1);
//        activeMenu.setIcon("el-icon-setting");
//        activeMenu.setChildren(new ArrayList<>());
//        return activeMenu;
//    }
//
//    public ActiveMenu createMenuItem(String menuName, String path) {
//        ActiveMenu activeMenu = new ActiveMenu();
////        activeMenu.setId(MyUtils.genUuid());
//        activeMenu.setTableName(menuName);
//        activeMenu.setPath(path);
//        activeMenu.setMenuType(2);
//        return activeMenu;
//    }
//
//    /**
//     * 批量注册子菜单
//     *
//     * @param activeMenu  菜单
//     * @param itemMenuMap 子菜单，参数1：Name，参数2：path
//     */
//    public void batchAddMenuItem(ActiveMenu activeMenu, Map<String, String> itemMenuMap) {
//        if (activeMenu == null)
//            return;
//
//        Set<String> keySet = itemMenuMap.keySet();
//        for (String menuName : keySet) {
//            activeMenu.getChildren().add(
//                    createMenuItem(menuName, itemMenuMap.get(menuName)));
//        }
//    }
//
//    /**
//     * 菜单转为动态菜单
//     *
//     * @param visitMenu 主菜单
//     * @return 动态菜单
//     */
//    public ActiveMenu transMenu(VisitMenu visitMenu) {
//        ActiveMenu activeMenu = new ActiveMenu();
//        activeMenu.setId(visitMenu.getId());
//        activeMenu.setTableName(visitMenu.getMenuName());
//        activeMenu.setMenuType(1);
//        activeMenu.setIcon("el-icon-setting");
//        activeMenu.setChildren(new ArrayList<>());
//        return activeMenu;
//    }
//}
