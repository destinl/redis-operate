package org.example.rank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/7/2222:26
 */
@Service
public class CartService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public void addToCart(String cartId, String productId, int quantity) {
        // 将购物车项存储到Redis的Hash结构中
        redisTemplate.opsForHash().put("cart:" + cartId, productId, String.valueOf(quantity));
    }

    public Map<String, Integer> getCart(String cartId) {
        // 从Redis获取购物车内容
        Map<Object, Object> cartEntries = redisTemplate.opsForHash().entries("cart:" + cartId);
        // 使用Stream API将值转换为Integer类型
        return cartEntries.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> (String) entry.getKey(),
                        entry -> {
                            Object value = entry.getValue();
                            return Integer.parseInt(value.toString());
                        }
                ));
    }
}
