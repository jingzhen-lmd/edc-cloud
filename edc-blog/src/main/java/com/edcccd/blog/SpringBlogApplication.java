package com.edcccd.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

@EnableOpenApi
@SpringBootApplication
public class SpringBlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBlogApplication.class, args);
    }
}
