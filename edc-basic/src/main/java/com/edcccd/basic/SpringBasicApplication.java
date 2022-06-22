package com.edcccd.basic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SpringBasicApplication {
  public static void main(String[] args) {
    SpringApplication.run(SpringBasicApplication.class);
  }


}
