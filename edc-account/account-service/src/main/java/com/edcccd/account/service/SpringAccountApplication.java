package com.edcccd.account.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SpringAccountApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringAccountApplication.class);
  }
}
