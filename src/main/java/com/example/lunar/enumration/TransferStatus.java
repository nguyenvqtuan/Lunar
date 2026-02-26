package com.example.lunar.enumration;

import lombok.Getter;

@Getter
public enum TransferStatus {
    NEW("New"),
    COMPLETED("Completed"),
    PROCESSING("Processing");

    private final String id;

    TransferStatus(String id) {
        this.id = id;
    }
}
