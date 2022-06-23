package com.edcccd.account;

import com.edcccd.account.mapper.UserMapper;
import com.edcccd.account.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
}
