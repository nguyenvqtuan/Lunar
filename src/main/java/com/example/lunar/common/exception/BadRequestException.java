package com.example.lunar.common.exception;

import lombok.Getter;

@Getter
public class BadRequestException extends RuntimeException {
  private final Object result;

  public BadRequestException(String message) {
    super(message);
    this.result = null;
  }

  public BadRequestException(String message, Object result) {
    super(message);
    this.result = result;
  }
}
