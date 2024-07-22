package org.example.utils;

import java.time.LocalDateTime;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/7/2222:01
 */
public class SampleTask implements Runnable {
    @Override
    public void run() {
        System.out.println("Task is running: " + LocalDateTime.now());
        // 执行任务逻辑
    }
}
