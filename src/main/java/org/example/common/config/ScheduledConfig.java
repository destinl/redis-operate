//package org.example.common.config;
//
//import org.redisson.api.RScheduledExecutorService;
//import org.redisson.api.RedissonClient;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * @Description:
// * @Author: ls
// * @Date: 2024/7/2222:46
// */
//@Configuration
//public class ScheduledConfig {
//
//    @Bean
//    public RScheduledExecutorService redissonScheduledExecutorService(RedissonClient redissonClient) {
//        return redissonClient.getExecutorService("myScheduler");
//    }
//}