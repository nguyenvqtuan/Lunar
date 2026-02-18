package com.example.lunar.enumration;

import lombok.Getter;

@Getter
public enum WalletStatus {
    ACTIVE("ACTIVE"),
    EXPIRED("EXPIRED");

    private final String id;

    WalletStatus(String id) {
        this.id = id;
    }
}
