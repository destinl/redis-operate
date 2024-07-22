package org.example.rank.controller;

import org.example.rank.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/7/2222:26
 */
@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/{cartId}/items")
    public ResponseEntity<String> addToCart(@PathVariable String cartId,
                                            @RequestParam String productId,
                                            @RequestParam int quantity) {
        cartService.addToCart(cartId, productId, quantity);
        return ResponseEntity.ok("Item added to cart");
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<Map<String, Integer>> getCart(@PathVariable String cartId) {
        Map<String, Integer> cart = cartService.getCart(cartId);
        return ResponseEntity.ok(cart);
    }
}