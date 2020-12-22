package com.huan.demo.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * <p>
 *     @EnableScheduling : 开启定时任务
 * </p>
 * @author 牟欢
 * @Classname StaticScheduleTask
 * @Description TODO
 * @Date 2020-12-22 14:59
 */
@Slf4j
@Configuration
@EnableScheduling
public class StaticScheduleTask {
    /**
     *  cron 详解：  https://blog.csdn.net/fanrenxiang/article/details/80361582
     */

    /**
     * 静态定时任务， 每隔五秒执行一次
     */
    /*@Scheduled(cron = "0/5 * * * * ?")
    private void doPrintTask(){
        log.info("每五秒打印一句话： HELLO WORLD");
    }*/
}
