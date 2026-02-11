package com.example.lunar.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "currency")
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String currency;

    private LocalDateTime deletedDate;

    private String deletedBy;

    private LocalDateTime updatedDate;

    private String updatedBy;

    private LocalDateTime createdDate;

    private String createdBy;
}
