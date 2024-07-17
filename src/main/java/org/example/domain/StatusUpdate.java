package org.example.domain;

import lombok.*;

import java.time.Instant;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/7/1716:03
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StatusUpdate {
    private String userId;
    private String content;
    private Instant timestamp;
    // 省略构造函数、getter和setter方法
}
