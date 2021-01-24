package com.huan.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *  <p>
 *      默认包结构扫描
 *          主程序所在包及其下面所有的子包都会被默认扫描
 *          无需提前包扫描配置
 *      手动配置包扫描结构 scanBasePackages
 *          @SpringBootApplication(scanBasePackages = "com.huan")
 *  </p>
 *
 *  1. @SpringBootApplication：这是要给SpringBoot应用
 *
 *
 *
 * @author mubaisama
 */
@SpringBootApplication(scanBasePackages = "com.huan")
@MapperScan("com.huan.demo.mapper")
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
