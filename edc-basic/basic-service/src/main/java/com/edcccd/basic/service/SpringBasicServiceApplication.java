package com.edcccd.basic.service;

import com.edcccd.account.api.feign.CheckClient;
import com.edcccd.basic.api.config.FeignConfig;
import com.edcccd.basic.api.feign.UserFeign;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(clients = { UserFeign.class, CheckClient.class },
    defaultConfiguration = FeignConfig.class)
public class SpringBasicServiceApplication {
  public static void main(String[] args) {
    SpringApplication.run(SpringBasicServiceApplication.class);
  }
}
