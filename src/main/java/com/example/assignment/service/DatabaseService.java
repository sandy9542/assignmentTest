package com.example.assignment.service;

import java.util.concurrent.CompletableFuture;

public interface DatabaseService {

    CompletableFuture<Void> processQuery(String query);
}
