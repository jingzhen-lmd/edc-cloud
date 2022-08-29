package com.edcccd.account.service;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.lang.Console;
import com.edcccd.common.util.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;

@SpringBootTest
public class MyTest {

    @Resource
    RedisUtil redisUtil;


    @Test
    public void test() {
        //定义图形验证码的长和宽
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100);


        //图形验证码写出，可以写出到文件，也可以写出到流
        //     lineCaptcha.write("d:/line.png");
        // 输出code
        Console.log(lineCaptcha.getCode());
        System.out.println(lineCaptcha.getImageBase64Data());
        // 验证图形验证码的有效性，返回boolean值
        // lineCaptcha.verify("1234");

        //重新生成验证码
        //     lineCaptcha.createCode();
        //     lineCaptcha.write("d:/line.png");
        //新的验证码
        //     Console.log(lineCaptcha.getCode());
        //验证图形验证码的有效性，返回boolean值
        lineCaptcha.verify("1234");
    }


    @Autowired
    PasswordEncoder encoder;

    @Test
    public void testmima() {
        boolean matches = encoder.matches("089275", "$2a$10$c6lYQ3LUnIFyV/jPVzLMG.QHkgFBSV0Xw0SqS2kA9z7vbMnU4RMyq");
        System.out.println(matches);

    }

    @Test
    public void tesasdast() {
        Studnet studnet = new Studnet("小明","男",15);

        redisUtil.addMap("zhenge", studnet,100, TimeUnit.MINUTES);

    }
}

class Studnet implements Serializable {
    String name;
    String sex;
    int age;

    public Studnet(String name, String sex, int age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
    }
}
