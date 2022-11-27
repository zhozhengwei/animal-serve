package com.zzw.animalserve.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @description(描述)
 * @autor: zhouzhengwei
 * @date: 2022/10/18__22:40
 */
@Configuration
public class BootConfig  implements WebMvcConfigurer {
    //跨域请求
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
//                .allowedOrigins("*") //允许跨域请求的 域名
                .allowedOriginPatterns("*")
                .allowCredentials(true) //是否允许cookie
                .allowedMethods("*") //允许的请求方式：GET、POST、DELETE、 PUT
                .allowedHeaders("*") //允许的header属性
                .maxAge(36000); //跨域如需时间
    }
}
