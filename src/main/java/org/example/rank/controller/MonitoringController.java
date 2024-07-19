package org.example.rank.controller;

import org.example.domain.CpuUsageData;
import org.example.rank.service.MonitoringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/7/1922:54
 */
@RestController
@RequestMapping("/monitoring")
public class MonitoringController {

    @Autowired
    private MonitoringService monitoringService;

    @PostMapping("/logCpuUsage")
    public ResponseEntity<String> logCpuUsage(@RequestParam String serverId, @RequestParam double cpuUsage) {
        monitoringService.logCpuUsage(serverId, cpuUsage);
        return ResponseEntity.ok("CPU usage logged");
    }

    @GetMapping("/cpuUsageHistory")
    public ResponseEntity<List<CpuUsageData>> getCpuUsageHistory(@RequestParam String serverId,
                                                                 @RequestParam Instant start,
                                                                 @RequestParam Instant end) {
        List<CpuUsageData> history = monitoringService.getCpuUsageHistory(serverId, start, end);
        return ResponseEntity.ok(history);
    }
}