package com.zzw.animalserve.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Knife4j 接口文档配置
 *
 * https://doc.xiaominfo.com/knife4j/documentation/get_start.html
 *
 * @author yupi
 */
@Configuration
@EnableSwagger2
public class Knife4jConfiguration{

    @Bean(value = "defaultApi2")
    public Docket defaultApi2() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("animal-serve")
                        .description("为野生动物网站提供网络服务")
                        .termsOfServiceUrl("https://github.com/zhozhengwei")
                        .contact(new Contact("zhouzhengwei", "https://github.com/zhozhengwei", "zzw17370709160@163.com"))
                        .version("1.0")
                        .build())
                .groupName("2.X版本")
                .select()
                // 这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.zzw.animalserve.controller"))
                .paths(PathSelectors.any())
                .build();
    }



}