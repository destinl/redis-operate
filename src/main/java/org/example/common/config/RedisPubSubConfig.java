package org.example.common.config;

import org.springframework.data.redis.connection.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/7/1415:45
 */
@Configuration
public class RedisPubSubConfig {

    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.redis.port}")
    private Integer redisPort;

    @Bean
    @Primary
    public ReactiveRedisConnectionFactory reactiveRedisConnectionFactory(LettuceClientConfiguration clientConfig) {
        // 创建Redis独立服务器配置
        RedisStandaloneConfiguration standaloneConfig = new RedisStandaloneConfiguration();
        standaloneConfig.setHostName(redisHost); // 设置主机名
        standaloneConfig.setPort(redisPort); // 设置端口号

        // 使用Lettuce连接工厂
        LettuceConnectionFactory connectionFactory = new LettuceConnectionFactory(standaloneConfig);

        // 返回响应式连接工厂
        return connectionFactory;
    }

    @Bean
    public RedisMessageListenerContainer pubSubredisMessageListenerContainer(ReactiveRedisConnectionFactory connectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory((RedisConnectionFactory) connectionFactory);
        container.addMessageListener( new PostNotificationListener(), new PatternTopic("newPostChannel"));
        return container;
    }

    @Bean
    public LettuceClientConfiguration clientConfig() {
        return LettuceClientConfiguration.defaultConfiguration();
    }

    @Component
    public class PostNotificationListener implements MessageListener {

//        @Override
//        public void onMessage(Message<String, String> message) {
//            // 处理接收到的帖子通知
//            String postContent = message.getBody();
//            System.out.println("New post notification received: " + postContent);
//            // 这里可以添加逻辑，比如通知关注者
//        }
//

        @Override
        public void onMessage(Message message, byte[] pattern) {
            String orderData = new String(message.getBody(), StandardCharsets.UTF_8);
            System.out.println("Processing order: " + orderData);
        }
    }
}