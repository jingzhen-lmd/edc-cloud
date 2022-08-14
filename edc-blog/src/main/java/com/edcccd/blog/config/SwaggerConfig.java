package com.edcccd.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
    /**
     * 创建Docker类型对象。
     * <p>docker是swagger的全局配置对象</p>
     */
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.OAS_30) // 使用3.0版本
                .apiInfo(apiInfo()) // 配置项目信息
                .select() // 获取选择器，指定扫描哪个包的启动器，不写，默认spring启动的范围
                .apis(RequestHandlerSelectors.basePackage("com.edcccd.blog.controller"))
                // .paths(PathSelectors.regex("/blog/article/info/*")) //正则表达。限定接口，可拼接多个
                .build();
    }

    /**
     * 用于配置swagger页面的项目信息
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("博客接口文档")
                .description("blog接口文档,用于测试接口")
                .contact(new Contact("ccd", "http:localhost:8091/account", "70600330@qq.com"))
                .version("1.0")
                .build();
    }


}