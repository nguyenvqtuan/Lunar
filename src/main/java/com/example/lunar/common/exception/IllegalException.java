package com.example.lunar.common.exception;

import lombok.Getter;

@Getter
public class IllegalException extends RuntimeException {

    private final Object result;

    public IllegalException(String message) {
        super(message);
        this.result = null;
    }

    public IllegalException(String message, Object result) {
        super(message);
        this.result = result;
    }
}
