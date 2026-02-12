package com.example.lunar.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseRestApi<T> {

    private static final String SUCCESS = "SUCCESS";

    private String requestId;

    private String timestamp;

    private String code;

    private String message;

    private T result;

    public static <T> ResponseRestApi<T> success() {
        return ResponseRestApi.<T>builder()
                .code(String.valueOf(HttpStatus.OK.value()))
                .message(SUCCESS)
                .requestId(UUID.randomUUID().toString())
                .timestamp(Instant.now().toString())
                .build();
    }

    public static <T> ResponseRestApi<T> success(T body) {
        return ResponseRestApi.<T>builder()
                .code(String.valueOf(HttpStatus.OK.value()))
                .message(SUCCESS)
                .requestId(UUID.randomUUID().toString())
                .timestamp(Instant.now().toString())
                .result(body)
                .build();
    }

    public static <T> ResponseRestApi<T> success(String requestId, T body) {
        return ResponseRestApi.<T>builder()
                .code(String.valueOf(HttpStatus.OK.value()))
                .message(SUCCESS)
                .requestId(requestId)
                .timestamp(Instant.now().toString())
                .result(body)
                .build();
    }
}
