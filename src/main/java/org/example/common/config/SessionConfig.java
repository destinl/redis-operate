package org.example.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/7/1513:16
 */
@Configuration
@EnableRedisHttpSession
public class SessionConfig {
}
