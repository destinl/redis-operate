package org.example.common.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/7/1716:53
 */
public class JacksonConfig {
    public static ObjectMapper createObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        //使用依赖下的JavaTimeModule模块
        mapper.registerModule(new JavaTimeModule()); // 注册 JavaTimeModule
        return mapper;
    }
}