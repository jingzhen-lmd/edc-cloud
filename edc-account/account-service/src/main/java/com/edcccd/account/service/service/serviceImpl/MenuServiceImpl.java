package com.edcccd.account.service.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.edcccd.account.api.entity.Menu;
import com.edcccd.account.service.mapper.MenuMapper;
import com.edcccd.account.service.service.MenuService;
import org.springframework.stereotype.Service;

@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

}

