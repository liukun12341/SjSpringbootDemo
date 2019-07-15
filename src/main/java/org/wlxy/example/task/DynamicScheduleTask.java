package org.wlxy.example.task;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

@Component
@Configuration // 标记为配置类
//@EnableScheduling // 开启定时任务
public class DynamicScheduleTask  implements SchedulingConfigurer {

    @Mapper
    public  interface  CronMapper{
        @Select("select cron from cron where id=#{id}")
        public  String getCron(String id);
    }

    @Autowired // 注入mapper
    @SuppressWarnings("all")
    CronMapper cronMapper;

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {

        scheduledTaskRegistrar.addTriggerTask(
                // 1.添加任务内容（Runnable）
                () -> System.out.println("执行动态定时任务"),
                // 2.设置执行周期（Trigger）
                TriggerContext ->{
                    String cron = cronMapper.getCron("execute_per_second");
                    return new CronTrigger(cron).nextExecutionTime(TriggerContext);

                }
        );
    }
}
