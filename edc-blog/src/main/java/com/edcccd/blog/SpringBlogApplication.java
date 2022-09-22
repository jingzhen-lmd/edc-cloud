package com.edcccd.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.oas.annotations.EnableOpenApi;

@EnableOpenApi
@SpringBootApplication
@EnableFeignClients(basePackages = "com.edcccd.account.api.feign")
public class SpringBlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBlogApplication.class, args);
    }
}
