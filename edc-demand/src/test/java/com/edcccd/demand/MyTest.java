package com.edcccd.demand;


import com.edcccd.demand.service.file.FileServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MyTest {

    @Autowired
    FileServiceImpl service;

    @Test
    public void ceshi() {


//        service.getSubPath("dasd/asd", "dasd/asd/54656");
//704368749818300
        System.out.println(System.nanoTime());
    }
}
