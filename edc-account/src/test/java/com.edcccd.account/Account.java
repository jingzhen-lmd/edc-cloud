package com.edcccd.account;

import com.edcccd.account.mapper.UserMapper;
import com.edcccd.account.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

@SpringBootTest
public class Account {

    @Autowired
    UserMapper mapper;

    @Test
    public void test1() {
        User user = new User();
        user.setUserName("xiaoming");
        user.setPassword("1234");
        user.setPhone("1456");
        user.setPower("1");
        user.setCheckCode("asd");
        int insert = mapper.insert(user);
        System.out.println(insert);
    }

    @Test
    public void testLog() throws IOException {
// 定义唯一标识，通常是类名
        Logger logger = Logger.getLogger("com.suibian.wahaha");
//        /log 目录必须存在，不能创建目录，智能创建文件？
        FileHandler fileHandler = new FileHandler(".//mylog.log");
        SimpleFormatter formatter = new SimpleFormatter();

        fileHandler.setFormatter(formatter);
        logger.addHandler(fileHandler);

        //        logger.log(Level.INFO,"hello");
        logger.severe("cuowu");
        logger.warning("tishi");
        logger.info("hello");
    }
}
