package com.example.lunar.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record WalletRequest(
        @Schema(description = "Tên tài khoản") @NotNull String userName,
        @Schema(description = "Loại tiền tệ") @NotNull String currency) {
}
