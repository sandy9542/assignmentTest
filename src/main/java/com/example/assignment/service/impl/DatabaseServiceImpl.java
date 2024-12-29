package com.example.assignment.service.impl;

import com.example.assignment.service.DatabaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class DatabaseServiceImpl implements DatabaseService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Async
    public CompletableFuture<Void> processQuery(String query) {
        return CompletableFuture.runAsync(() -> {
            try {
                if (query.trim().toUpperCase().startsWith("SELECT")) {
                    jdbcTemplate.query(query, rs -> {
                        System.out.println("Data: " + rs.getString(1));
                    });
                } else {
                    int rowsAffected = jdbcTemplate.update(query);
                    System.out.println("Rows affected: " + rowsAffected);
                }
            } catch (Exception e) {
                System.err.println("Database error: " + e.getMessage());
            }
        });
    }
}
