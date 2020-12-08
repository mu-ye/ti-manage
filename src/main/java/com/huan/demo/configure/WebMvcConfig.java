package com.huan.demo.configure;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 1. 配置跨域处理
 * 2. 配置静态文件映射
 * 3. 配置拦截器
 * @author mubaisama
 */
public class WebMvcConfig implements WebMvcConfigurer {


    /**
     * 解决跨域问题
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedMethods("*");
    }
}
