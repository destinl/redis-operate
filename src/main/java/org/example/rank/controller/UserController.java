package org.example.rank.controller;

import org.example.rank.service.OnlineDurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/7/1221:40
 */
@Controller
public class UserController {

    @Autowired
    private OnlineDurationService onlineDurationService;

    @PostMapping("/user/{userId}/login")
    public ResponseEntity<String> userLogin(@PathVariable String userId) {
        // 用户登录逻辑，可以是任何触发登录的事件
        // 这里应该再加不能重复登录校验之类的。。。
        //没有区分开login和logout。。。只对updateUserOnlineDuration内进行了开始和结束区分。。。
        onlineDurationService.updateUserOnlineDuration(userId, System.currentTimeMillis());
        return ResponseEntity.ok("User " + userId + " logged in");
    }

    @PostMapping("/user/{userId}/logout")
    public ResponseEntity<String> userLogout(@PathVariable String userId) {
        // 用户登出时记录在线时长
        // 计算用户在线时长的逻辑
        onlineDurationService.updateUserOnlineDuration(userId, System.currentTimeMillis());
        return ResponseEntity.ok("User " + userId + " logged out");
    }
}