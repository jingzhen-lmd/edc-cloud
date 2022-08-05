package com.edcccd.gateway;

import com.edcccd.account.api.feign.CheckClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients(clients = CheckClient.class)
public class SpringGatewayApplication {
  public static void main(String[] args) {
    SpringApplication.run(SpringGatewayApplication.class, args);
  }
}
