package org.example.rank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/7/1415:45
 */
@Service
public class PostService {

    @Autowired
    private ReactiveRedisTemplate<String, String> reactiveRedisTemplate;

    public Mono<Long> publishNewPostNotification(String postContent) {
        return reactiveRedisTemplate.convertAndSend("newPostChannel", postContent);
    }
}