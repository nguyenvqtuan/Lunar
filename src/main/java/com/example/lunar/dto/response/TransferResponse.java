package com.example.lunar.dto.response;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransferResponse {

    private String transactionId;
    private String status;
    private BigDecimal amount;
    private String fromWalletId;
    private String toWalletId;
}
