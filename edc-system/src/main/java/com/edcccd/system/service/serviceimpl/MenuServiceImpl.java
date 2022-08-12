package com.edcccd.system.service.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.edcccd.common.util.Result;
import com.edcccd.system.entity.Menu;
import com.edcccd.system.entity.MenuFilter;
import com.edcccd.system.mapper.MenuMapper;
import com.edcccd.system.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    MenuMapper mapper;


    @Override
    public Result<List<Menu>> queryByFilter(MenuFilter filter) {
        LambdaQueryWrapper<Menu> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Menu::getProjectId, filter.getProjectId());
        wrapper.eq(Menu::getPartId, filter.getPartId());
        List<Menu> menus = mapper.selectList(wrapper);

        return Result.success(menus);
    }

    @Override
    public Result<List<Menu>> query(String projectId, String partId) {
        LambdaQueryWrapper<Menu> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Menu::getProjectId, projectId);
        wrapper.eq(Menu::getPartId, partId);
        List<Menu> menus = mapper.selectList(wrapper);

        return Result.success(menus);
    }
}
