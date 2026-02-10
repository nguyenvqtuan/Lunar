package com.example.lunar.enumration;

import lombok.Getter;

@Getter
public enum ErrorCode {
  RESOURCE_NOT_FOUND("RESOURCE_NOT_FOUND", "Resource not found!"),
  VALIDATION("VALIDATION", "Validation is in valid!"),
  BAD_REQUEST("BAD_REQUEST", "Bad request"),
  ILLEGAL("ILLEGAL", "Illegal parameters");

  private final String code;
  private final String message;

  ErrorCode(String code, String message) {
    this.code = code;
    this.message = message;
  }
}
