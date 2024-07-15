package org.example.rank.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/7/1513:17
 */
@Controller
public class SessionController {

    // 用户登录后，Spring Session会自动存储会话信息到Redis,加了3个，到时间只删了2个。执行logout才把最后一个删了
    @PostMapping("/login")
    public ResponseEntity<String> login(HttpSession httpSession, String username) {
        httpSession.setAttribute("username", username);
        return ResponseEntity.ok("loginSuccess");
    }

    // 用户登出时，清除会话信息
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession httpSession) {
        httpSession.invalidate();
        return ResponseEntity.ok("logoutSuccess");
    }
}
