package com.example.assignment;

import com.example.assignment.redis.RedisService;
import com.example.assignment.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AssignmentApplication {

    @Autowired
    public DatabaseService databaseService;

    @Autowired
    public RedisService redisService;

    public static void main(String[] args) {
        SpringApplication.run(AssignmentApplication.class, args);
    }

    public void run(String args) throws Exception {
        databaseService.processQuery("SELECT * FROM your_table");
        redisService.cacheData("sampleKey", "sampleValue");
        System.out.println("Cached Value: " + redisService.getCachedData("sampleKey"));
    }
}
