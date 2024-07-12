package org.example.message;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/7/1218:52
 */
@Component
public class MessageListenerImpl implements MessageListener {
    @Override
    public void onMessage(Message message, byte[] pattern) {
//        String orderData = Arrays.toString(message.getBody());
        String orderData = new String(message.getBody(), StandardCharsets.UTF_8);
        processOrder(orderData);
    }

    private void processOrder(String orderData) {
        // 处理订单逻辑
        System.out.println("Processing order: " + orderData);
        // 假设订单处理成功
    }
}
