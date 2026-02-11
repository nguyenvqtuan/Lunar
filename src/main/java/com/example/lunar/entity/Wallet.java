package com.example.lunar.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "wallet")
@Getter
@Setter
@NoArgsConstructor
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private UUID userId;

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
