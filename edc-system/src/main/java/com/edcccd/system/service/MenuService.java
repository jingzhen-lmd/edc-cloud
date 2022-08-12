package com.edcccd.system.service;

import com.edcccd.common.util.Result;
import com.edcccd.system.entity.Menu;
import com.edcccd.system.entity.MenuFilter;

import java.util.List;

public interface MenuService{

    /**
     * 查询指定项目的目录
     *
     * @param filter 菜单筛选条件
     * @return 单条数据
     */
    Result<List<Menu>> queryByFilter(MenuFilter filter);

    Result<List<Menu>> query(String projectId, String partId);
}
