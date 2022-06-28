//package com.edcccd.system.service.menu.serviceImpl;
//
//import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
//import com.edcccd.system.mapper.visit.ActiveMenuMapper;
//import com.edcccd.system.pojo.generate.ActiveMenu;
//import com.edcccd.system.service.menu.ActiveMenuService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class ActiveMenuServiceImpl extends ServiceImpl<ActiveMenuMapper, ActiveMenu> implements ActiveMenuService {
//
//    @Autowired
//    ActiveMenuMapper mapper;
//
//    @Override
//    public List<ActiveMenu> queryActive(String menuUuid) {
//        List<ActiveMenu> activeMenus = mapper.queryActive(menuUuid);
//        return activeMenus;
//    }
//}
