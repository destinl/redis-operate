package org.example.common.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/7/1414:39
 */
@Configuration
public class RedissonConfig {

    @Bean(destroyMethod = "shutdown")
    public RedissonClient redissonClient() {
//        RedissonClientConfig config = new RedissonClientConfig();
//        config.useSingleServer().setAddress(redisson.address);

        Config config = new Config();
        config.useSingleServer().setAddress(redissonAddress);
        return Redisson.create(config);
    }

    @Value("${spring.redisson.address}")
    private String redissonAddress;
}
