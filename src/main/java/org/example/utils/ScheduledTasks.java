//package org.example.utils;
//
//import org.example.rank.service.ScheduledTaskService;
//import org.redisson.api.RScheduledExecutorService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.util.concurrent.TimeUnit;
//
///**
// * @Description:
// * @Author: ls
// * @Date: 2024/7/2222:48
// */
//@Component
//public class ScheduledTasks {
//
//    @Autowired
//    private ScheduledTaskService taskService;
//
//    @Autowired
//    private RScheduledExecutorService scheduler;
//
//    @Scheduled(cron = "58 22 * * * ?") // 每天凌晨1点执行
//    public void scheduledTask() {
//        scheduler.schedule(() -> taskService.executeTask(), 0, TimeUnit.SECONDS);
//    }
//}
