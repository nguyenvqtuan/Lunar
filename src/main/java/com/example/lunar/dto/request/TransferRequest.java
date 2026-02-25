package com.example.lunar.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransferRequest {

    @NotBlank
    private String requestId; // UUID from client (idempotency key)

    @NotBlank
    private String fromWalletId;

    @NotBlank
    private String toWalletId;

    @NotNull
    @Positive
    private BigDecimal amount;
}
