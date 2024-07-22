package org.example.rank.controller;

import org.example.rank.service.TaskSchedulingService;
import org.example.utils.SampleTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/7/2221:54
 */
@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskSchedulingService taskSchedulingService;

    @PostMapping("/schedule")
    public ResponseEntity<String> scheduleTask(@RequestParam long delay, @RequestParam TimeUnit timeUnit) {
        taskSchedulingService.scheduleTask(new SampleTask(), delay, timeUnit);
        return ResponseEntity.ok("Task scheduled for execution at " + LocalDateTime.now().plusNanos(timeUnit.toNanos(delay)));
    }
}