package com.edcccd.basic.service;

import com.edcccd.basic.api.feign.UserFeign;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(clients = {UserFeign.class})
public class SpringBasicServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBasicServiceApplication.class);
    }
}
