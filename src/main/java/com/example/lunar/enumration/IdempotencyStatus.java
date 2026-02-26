package com.example.lunar.enumration;

import lombok.Getter;

@Getter
public enum IdempotencyStatus {
    NEW("New"),
    COMPLETED("Completed"),
    PROCESSING("Processing"),
    FAILED("Failed");

    private final String id;

    IdempotencyStatus(String id) {
        this.id = id;
    }
}
