package org.wlxy.example.common.task;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
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
        @Select("select cron_expression from cron where id=#{id}")
        public  String getCronById(String id);
    }

    @Autowired // 注入mapper
    @SuppressWarnings("all")
    CronMapper cronMapper;

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.addTriggerTask(
                ()-> System.out.println("执行动态定时任务"), //定时任务执行的内容
                triggerContext -> { // 定时任务执行的时间  从数据库动态获取
                    String cron = cronMapper.getCronById("execute_per_second");
                    return  new CronTrigger(cron).nextExecutionTime(triggerContext);
                }
        );
    }
}
