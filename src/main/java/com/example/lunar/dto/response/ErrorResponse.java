package com.example.lunar.dto.response;

import java.time.Instant;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private String requestId;
    private Instant timestamp;
    private String code;
    private String message;
    private Object result;

    public ErrorResponse(String code, String message, Object result) {
        this.requestId = UUID.randomUUID().toString();
        this.timestamp = Instant.now();
        this.code = code;
        this.message = message;
        this.result = result;
    }

    public ErrorResponse(String code, String message) {
        this.requestId = UUID.randomUUID().toString();
        this.timestamp = Instant.now();
        this.code = code;
        this.message = message;
    }
}
