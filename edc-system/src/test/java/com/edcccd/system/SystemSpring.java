package com.edcccd.system;

import com.edcccd.system.mapper.visit.VisitMapper;
import com.edcccd.system.pojo.menu.VisitMenu;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SystemSpring {

    @Autowired
    VisitMapper mapper;

    @Test
    public void test(){
    }
}
