package com.edcccd.system.service.menu.serviceImpl;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.edcccd.system.mapper.visit.VisitMenuItemMapper;
import com.edcccd.system.pojo.menu.VisitMenuItem;
import com.edcccd.system.service.menu.VisitMenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisitMenuItemServiceImpl extends ServiceImpl<VisitMenuItemMapper, VisitMenuItem> implements VisitMenuItemService {

    @Autowired
    VisitMenuItemMapper mapper;

    @Override
    public void saveItems(String menuUuid, List<VisitMenuItem> items) throws Exception {
//        1、删除
        QueryWrapper<VisitMenuItem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("visitMenuUuid", menuUuid);
        mapper.delete(queryWrapper);

//        2、保存
        for (int i = 0; i < items.size(); i++) {
            VisitMenuItem item = items.get(i);
            item.setVisitMenuId(menuUuid);
            item.setSequence(i);
            mapper.insert(item);
        }
    }

    @Override
    public List<VisitMenuItem> listVisitMenuItem(String visitMenuUuid) {
        QueryWrapper<VisitMenuItem> wrapper = new QueryWrapper<>();
        wrapper.eq("visitMenuUuid", visitMenuUuid);
        wrapper.orderByAsc("sequence");

        return mapper.selectList(wrapper);
    }

}
