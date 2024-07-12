package org.example.rank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/7/1221:41
 */
@Service
public class OnlineDurationService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public void updateUserOnlineDuration(String userId, long duration) {
        String lastLoginTime = redisTemplate.opsForValue().get(userId);
        if (lastLoginTime != null) {
            // 使用Sorted Set存储用户ID和在线时长
            redisTemplate.opsForZSet().incrementScore("user:online:duration", userId,
                    duration-Long.parseLong(lastLoginTime));
            return;
        }
        // 将查询结果放入缓存，设置过期时间为1分钟
        redisTemplate.opsForValue().set(userId, String.valueOf(duration), 1, TimeUnit.MINUTES);
        //这里没有处理过期后缓存怎么办，直接设置 上次登录时间 过期后重新计算在线时长。。。
        redisTemplate.opsForZSet().incrementScore("user:online:duration", userId,
                0);
    }

    public Set<String> getTopUsersByOnlineDuration(int topN) {
        // 获取在线时长最长的前N个用户
        Set<String> topUsers = redisTemplate.opsForZSet().reverseRange("user:online:duration", 0, topN - 1);
        return topUsers;
    }
}
