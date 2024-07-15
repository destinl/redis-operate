package org.example.interceptor;

import org.example.annotation.RateLimit;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;
import org.aspectj.lang.reflect.MethodSignature;
/**
 * @Description:
 * @Author: ls
 * @Date: 2024/7/1423:02
 */
@Component
public class RateLimiterInterceptor implements HandlerInterceptor {
    private final RedisTemplate<String, Integer> redisTemplate;
    private final String rateLimitKeyPrefix = "rate_limit:";

    public RateLimiterInterceptor(RedisTemplate<String, Integer> redisTemplate_String_Integer) {
        this.redisTemplate = redisTemplate_String_Integer;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)){
            return true;//web.servlet.resource.ResourceHttpRequestHandler 对象强制转换为 HandlerMethod 类型会报错
        }
        if (!(((HandlerMethod) handler).getMethod().isAnnotationPresent(RateLimit.class))) {
            return true;//or false,false会返回连接错误
        }
        RateLimit rateLimitAnnotation = ((HandlerMethod) handler).getMethod().getAnnotation(RateLimit.class);
        String ip = request.getRemoteAddr();
        String methodName =  handler.toString();
        String rateLimitKey = rateLimitKeyPrefix + methodName + ":" + ip;

        int currentCount = Math.toIntExact(redisTemplate.opsForValue().increment(rateLimitKey));
        if (currentCount > rateLimitAnnotation.limit()) {
            // 如果当前计数大于1，则说明请求已超过限制
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Too many requests, please try again later.");
            response.setStatus(429);//返回结果400BAD_REQUEST，怎么看是否有输出给定信息和状态
            return false;
        }

        // 设置过期时间
        redisTemplate.expire(rateLimitKey,
                rateLimitAnnotation.timeout(),
                TimeUnit.SECONDS);
        return true;

    }
}
