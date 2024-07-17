package org.example.rank.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.domain.StatusUpdate;
import org.example.rank.service.SocialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/7/1715:59
 */
@RestController
@RequestMapping("/social")
public class SocialController {

    @Autowired
    private SocialService socialService;

    @PostMapping("/addFriend")
    public ResponseEntity<String> addFriend(@RequestParam String userOneId, @RequestParam String userTwoId) {
        socialService.addFriend(userOneId, userTwoId);
        return ResponseEntity.ok("Friends added successfully");
    }

    @GetMapping("/friends/{userId}")
    public ResponseEntity<Set<String>> getFriends(@PathVariable String userId) {
        Set<String> friends = socialService.getFriends(userId);
        return ResponseEntity.ok(friends);
    }

    @PostMapping("/status")
    public ResponseEntity<String> postStatusUpdate(@RequestParam String userId, @RequestParam String content) throws JsonProcessingException {
        socialService.postStatusUpdate(userId, content);
        return ResponseEntity.ok("Status updated successfully");
    }

    @GetMapping("/timeline/{userId}")
    public ResponseEntity<List<StatusUpdate>> getStatusUpdates(@PathVariable String userId) throws IOException {
        List<StatusUpdate> updates = socialService.getStatusUpdates(userId);
        return ResponseEntity.ok(updates);
    }
}