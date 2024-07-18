package org.example.rank.controller;

import org.example.rank.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/7/1817:57
 */
@RestController
@RequestMapping("/recommendations")
public class RecommendationController {

    @Autowired
    private RecommendationService recommendationService;

    @PostMapping("/view")
    public ResponseEntity<String> recordProductView(@RequestParam String userId, @RequestParam String productId) {
        recommendationService.recordView(userId, productId);
        return ResponseEntity.ok("View recorded");
    }

    @GetMapping("/products")
    public ResponseEntity<List<String>> getRecommendations(@RequestParam String userId) {
        List<String> recommendedProducts = recommendationService.recommendProducts(userId);
        return ResponseEntity.ok(recommendedProducts);
    }
}