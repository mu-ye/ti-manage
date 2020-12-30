package com.huan.demo.controller;


import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author MuBaiSama
 * @since 2020-12-15
 */
@Data
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {


    private final StringRedisTemplate redisTemplate;

    @GetMapping("/getOne")
    String getOne(){
        ValueOperations<String,String> operations = redisTemplate.opsForValue();
        operations.set("hello","world");
        return operations.get("hello");
    }

}

