package com.example.lunar.dto.command;

import java.math.BigDecimal;
import lombok.Builder;

@Builder
public record TransferCommand(
        String idempotencyKey,
        Long fromWalletId,
        Long toWalletId,
        BigDecimal amount,
        String status,
        String createdDate) {}
