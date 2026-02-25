package com.example.lunar.dto.command;

import java.math.BigDecimal;
import lombok.Builder;

@Builder
public record WalletCommand(
        String userName,
        String currency,
        Long fromWalletId,
        Long toWalletId,
        BigDecimal originalBalance,
        BigDecimal movementBalance,
        BigDecimal amount) {}
