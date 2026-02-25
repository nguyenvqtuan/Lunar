package com.example.lunar.dto.response;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Builder
public class IdempotencyResult {

    private boolean acquired;
    private boolean processing;
    private boolean completed;
    private Long transactionId;

    public static IdempotencyResult acquired() {
        return IdempotencyResult.builder().acquired(true).build();
    }

    public static IdempotencyResult processing() {
        return IdempotencyResult.builder().processing(true).build();
    }

    public static IdempotencyResult completed(Long txId) {
        return IdempotencyResult.builder().completed(true).transactionId(txId).build();
    }
}
