package org.example.rank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/7/1218:55
 */
@Service
public class OrderService {
    @Autowired
    private ReactiveRedisTemplate<String, String> reactiveRedisTemplate;

    public Mono<Void> placeOrder(String orderData){
        return reactiveRedisTemplate.convertAndSend("order-channel", orderData)
                .then();
    }
}
