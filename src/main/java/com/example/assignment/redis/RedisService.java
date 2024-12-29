package com.example.assignment.redis;

import java.util.concurrent.CompletableFuture;

public interface RedisService {
    CompletableFuture<Void> cacheData(String key, String value);

    String getCachedData(String key);
}
