package com.huan.demo.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author MuBaiSama
 * @since 2020-12-15
 */
@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {


    private final StringRedisTemplate redisTemplate;


    /**
     * 将数据缓存进 redis , 并设置缓存时间
     */
    @GetMapping("/setRedis")
    String setRedis(){
        ValueOperations<String,String> operations = redisTemplate.opsForValue();
        operations.set("hello","world",10, TimeUnit.SECONDS);
        return "设置缓存成功";
    }

    /**
     * 判断缓存是否过期，如果没有过期，返回redis 存放数据
     * @return
     */
    @GetMapping("/getRedis")
    String getRedis(){
        ValueOperations<String,String> operations = redisTemplate.opsForValue();
        if(redisTemplate.hasKey("hello")){
            String redisText = operations.get("hello");
            System.out.println("获取缓存中数据"+redisText);
            return redisText;
        }else {
            log.info("缓存已过期");
            return "缓存已过期";
        }
    }

}

