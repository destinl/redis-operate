package org.example.rank.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.common.config.JacksonConfig;
import org.example.domain.StatusUpdate;
import org.example.utils.RedisSerialization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/7/1716:00
 */
@Service
public class SocialService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public void addFriend(String userOneId, String userTwoId) {
        // 使用集合存储用户的好友列表
        redisTemplate.opsForSet().add("friends:" + userOneId, userTwoId);
        redisTemplate.opsForSet().add("friends:" + userTwoId, userOneId);
    }

    public Set<String> getFriends(String userId) {
        // 获取用户的好友列表
        return redisTemplate.opsForSet().members("friends:" + userId);
    }

    public void postStatusUpdate(String userId, String content) throws JsonProcessingException {
        // 使用列表存储用户的状态更新时间线
        StatusUpdate statusUpdate = new StatusUpdate(userId, content, Instant.now());
        //, String.valueOf(statusUpdate.getTimestamp())
        ObjectMapper objectMapper = JacksonConfig.createObjectMapper();
        String jsonContent = objectMapper.writeValueAsString(statusUpdate);
        redisTemplate.opsForList().rightPush("timeline:" + userId, jsonContent);
    }

    public List<StatusUpdate> getStatusUpdates(String userId) throws IOException {
        // 获取用户的状态更新时间线
        List<String> stringList = redisTemplate.opsForList().range("timeline:" + userId, 0, -1);
        List<StatusUpdate> statusUpdates = new ArrayList<>();
        for(String sl: stringList){
            StatusUpdate statusUpdate = RedisSerialization.deserializeStatusUpdate(sl);
            statusUpdates.add(statusUpdate);
        }
        return statusUpdates;

    }
}
