package com.example.lunar.enumration;

import lombok.Getter;

@Getter
public enum LedgerType {
    CREDIT("Credit"),
    DEBIT("Debit");

    private final String id;

    LedgerType(String id) {
        this.id = id;
    }
}
