package com.huan.demo.task;

import com.huan.demo.service.CronService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

/**
 * @author 牟欢
 * @Classname DynamicScheduleTask
 * @Description TODO
 * @Date 2020-12-22 15:31
 */
@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class DynamicScheduleTask implements SchedulingConfigurer {
    private final CronService cronService;

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        /*taskRegistrar.addTriggerTask(
                () -> {
                    System.out.println("执行定时任务");
                },
                triggerContext -> {
                    //2.1 从数据库获取执行周期
                    String cron = cronService.getById(1).getCron();
                    //2.2 合法性校验.
                    if (StringUtils.isEmpty(cron)) {
                        // Omitted Code ..
                    }
                    //2.3 返回执行周期(Date)
                    return new CronTrigger(cron).nextExecutionTime(triggerContext);
                }
        );*/
    }
}
