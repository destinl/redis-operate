package org.example.common.config;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;

import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/7/1513:36
 */
@Configuration
public class SessionTimeoutConfig {
    @Bean
    public ConfigurableServletWebServerFactory webServerFactory() {
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
        factory.getSession().setTimeout(Duration.ofMinutes(1));// 设置会话超时时间（以分钟为单位）

        return factory;
    }
}
