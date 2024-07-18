package org.example.common.config;

/**
 * @Description: TODO
 * @Author: ls
 * @Date: 2024/6/1111:44
 */

import org.example.domain.User;
import org.example.message.MessageListenerImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory){
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        return template;
    }

    @Bean
    public RedisTemplate<String, Integer> redisTemplate_String_Integer(RedisConnectionFactory factory){
        RedisTemplate<String, Integer> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        return template;
    }

    @Bean
    public RedisTemplate<String, User> redisTemplate_String_User(RedisConnectionFactory factory){
        RedisTemplate<String, User> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        return template;
    }

    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer(RedisConnectionFactory connectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(new MessageListenerImpl(), new PatternTopic("order-channel"));
        return container;
    }

}
