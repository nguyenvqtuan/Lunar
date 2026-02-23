package com.example.lunar.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class WalletResponse {

    private String userName;
    private UUID currencyId;
    private String status;
    private BigDecimal balance;
    private LocalDateTime deletedDate;
    private String deletedBy;
    private LocalDateTime updatedDate;
    private String updatedBy;
    private LocalDateTime createdDate;
    private String createdBy;
}
