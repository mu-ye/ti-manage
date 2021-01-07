package com.huan.demo.controller;

import com.huan.demo.param.FormParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDate;

/**
 * @author 牟欢
 * @Classname TestController
 * @Description TODO
 * @Date 2021-01-07 14:42
 */
@Slf4j
@RestController
public class TestController {


    @GetMapping("/form")
    FormParam postForm(@Valid  FormParam formParam){
        log.info("formParam{}",formParam.getDate());
        return formParam;
    }

    public static void main(String[] args) {
        System.out.println(LocalDate.now().toString());
    }
}
