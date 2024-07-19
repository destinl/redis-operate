package org.example.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/7/1922:55
 */
@Data
@AllArgsConstructor
public class CpuUsageData {
    private Instant timestamp;
    private double cpuUsage;

    // 省略构造函数、getter和 setter 方法
}
