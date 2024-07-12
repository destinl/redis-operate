package org.example.rank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @Description: TODO
 * @Author: ls
 * @Date: 2024/6/1111:48
 */
@Service
public class RankService {
    private final StringRedisTemplate redisTemplate;

    private final String PREFIX = "leaderboerd:";

    @Autowired
    public RankService(StringRedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    public void updateScore(String userId, double score){
        redisTemplate.opsForZSet().incrementScore(PREFIX,userId,score);
    }

    public Set<String> getLeaders(int start, int stop){
        return redisTemplate.opsForZSet().reverseRange(PREFIX,start,stop);
    }
}
