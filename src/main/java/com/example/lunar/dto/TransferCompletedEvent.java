package com.example.lunar.dto;

import java.math.BigDecimal;

public record TransferCompletedEvent(Long transactionId, Long fromWalletId, Long toWalletId, BigDecimal amount) {}
