package com.edcccd.account;

import com.edcccd.account.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class Account {

    @Autowired
    UserMapper mapper;
    @Autowired
    PasswordEncoder encoder;

    @Test
    public void test1() {
        System.out.println(mapper.listUserPower(1L));
    }


    @Test
    void tewst2() {
        String str = "$2a$10$X5iwQAG.MCmK/3qpd.Px5.uNH98atwRiWNeTHmzIP/32r4KOCv5yq";
        System.out.println(encoder.matches("1", str));
    }
}
