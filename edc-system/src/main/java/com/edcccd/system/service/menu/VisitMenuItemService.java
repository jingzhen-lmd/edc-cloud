package com.edcccd.system.service.menu;

import com.baomidou.mybatisplus.extension.service.IService;
import com.edcccd.system.pojo.menu.VisitMenuItem;

import java.util.List;

public interface VisitMenuItemService extends IService<VisitMenuItem> {

    void saveItems(String menuUuid, List<VisitMenuItem> items) throws Exception;

    /**
     * 查询菜单下所有子菜单明细
     *
     * @param visitMenuUuid 主菜单uuid
     * @return 菜单明细（表）
     */
    List<VisitMenuItem> listVisitMenuItem(String visitMenuUuid);


}
