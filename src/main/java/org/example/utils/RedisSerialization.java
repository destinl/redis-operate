package org.example.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.common.config.JacksonConfig;
import org.example.domain.StatusUpdate;

import java.io.IOException;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/7/1716:59
 */
public class RedisSerialization {

    private static final ObjectMapper objectMapper = JacksonConfig.createObjectMapper();

    public static StatusUpdate deserializeStatusUpdate(String json) throws IOException {
        // 使用TypeReference来指定反序列化的目标类型
        return objectMapper.readValue(json, new TypeReference<StatusUpdate>() {});
    }
}