package com.edcccd.demand;

import com.edcccd.basic.api.feign.UserFeign;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(clients = {UserFeign.class})
public class SpringDemandApplication {
  public static void main(String[] args) {
    SpringApplication.run(SpringDemandApplication.class);
  }
}
