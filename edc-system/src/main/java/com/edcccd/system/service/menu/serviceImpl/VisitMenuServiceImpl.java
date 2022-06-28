package com.edcccd.system.service.menu.serviceImpl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.edcccd.system.mapper.visit.VisitMenuItemMapper;
import com.edcccd.system.mapper.visit.VisitMenuMapper;
import com.edcccd.system.pojo.BaseTable;
import com.edcccd.system.pojo.menu.VisitMenu;
import com.edcccd.system.service.menu.VisitMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VisitMenuServiceImpl extends ServiceImpl<VisitMenuMapper, VisitMenu> implements VisitMenuService {

    @Autowired
    VisitMenuMapper mapper;
    @Autowired
    VisitMenuItemMapper itemMapper;

    @Transactional
    @Override
    public void saveMenu(List<VisitMenu> menus) throws Exception {
//        1、校验数据合法性
        String projectId = menus.get(0).getProjectId();
        String centerId = menus.get(0).getCenterId();
        if (StrUtil.isBlank(centerId)) {
            throw new Exception("缺少项目、中心号:" + projectId + centerId);
        }

//        2、删除之前菜单下存在的所有菜单、先不删除子菜单
        QueryWrapper<VisitMenu> wrapper = new QueryWrapper<>();
        wrapper.eq("project_id", projectId);
        wrapper.eq("center_id", centerId);

        List<VisitMenu> visitMenus = mapper.selectList(wrapper);

//       3、删除子菜单
        for (VisitMenu menu : visitMenus) {
            itemMapper.deleteByVisitMenuId(menu.getId());
        }
//      4、删除菜单
        mapper.deleteBatchIds(visitMenus.stream()
                .map(BaseTable::getId)
                .collect(Collectors.toList()));

        // 5、保存所有菜单
        for (int i = 0; i < menus.size(); i++) {
            VisitMenu menu = menus.get(i);
            menu.setSequence(String.valueOf(i));
            mapper.insert(menu);
        }
    }

    @Override
    public List<VisitMenu> getMenu(String projectId, String centerId) {
        QueryWrapper<VisitMenu> wrapper = new QueryWrapper<>();
        wrapper.eq("project_id", projectId);
        wrapper.eq("center_id", centerId);
        wrapper.orderByAsc("sequence");

        return mapper.selectList(wrapper);
    }


}
