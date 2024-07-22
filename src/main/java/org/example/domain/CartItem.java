package org.example.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/7/2222:27
 */
@Data
@AllArgsConstructor
public class CartItem {
    private String productId;
    private int quantity;
    // 省略构造函数、getter和setter方法
}