package com.example.assignment.redis.impl;

import com.example.assignment.redis.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class RedisServiceImpl implements RedisService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Async
    public CompletableFuture<Void> cacheData(String key, String value) {
        return CompletableFuture.runAsync(() -> {
            try {
                redisTemplate.opsForValue().set(key, value);
                System.out.println("Cached data: " + key);
            } catch (Exception e) {
                System.err.println("Redis connection error: " + e.getMessage());
            }
        });
    }

    @Override
    public String getCachedData(String key) {
        try {
            return redisTemplate.opsForValue().get(key);
        } catch (Exception e) {
            System.err.println("Redis connection error: " + e.getMessage());
            return null;
        }
    }
}
