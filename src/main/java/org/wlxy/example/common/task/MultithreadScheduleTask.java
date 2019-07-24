package org.wlxy.example.common.task;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
//@EnableScheduling
@EnableAsync // 开启多线程
public class MultithreadScheduleTask {
    @Async
    @Scheduled(fixedDelay = 1000)
    public void first() throws InterruptedException{
        System.out.println("第一个定时任务开始："+ LocalDateTime.now().toLocalTime()+"\r\n线程"+Thread.currentThread().getName());
        System.out.println();
    }
    @Async
    @Scheduled(fixedDelay = 2000)
    public void second() throws InterruptedException{
        System.out.println("第二个定时任务开始："+ LocalDateTime.now().toLocalTime()+"\r\n线程"+Thread.currentThread().getName());
        System.out.println();
    }

}
