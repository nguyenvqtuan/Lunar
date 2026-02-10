package com.example.lunar.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record WalletRequest(
        @Schema(description = "") @NotNull String userId,
        @Schema(description = "") @NotNull String currency) {
}

