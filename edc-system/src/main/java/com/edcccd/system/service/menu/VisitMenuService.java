package com.edcccd.system.service.menu;

import com.baomidou.mybatisplus.extension.service.IService;
import com.edcccd.system.pojo.menu.VisitMenu;

import java.util.List;

public interface VisitMenuService extends IService<VisitMenu> {

    void saveMenu(List<VisitMenu> menus) throws Exception;

    /**
     * 通过基本信息，获取目录
     */
    List<VisitMenu> getMenu(String projectId,String centerId);
}
