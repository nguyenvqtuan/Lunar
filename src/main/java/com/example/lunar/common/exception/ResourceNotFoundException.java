package com.example.lunar.common.exception;

import lombok.Getter;

@Getter
public class ResourceNotFoundException extends RuntimeException {

  private final Object result;

  public ResourceNotFoundException(String message) {
    super(message);
    this.result = null;
  }

  public ResourceNotFoundException(String message, Object result) {
    super(message);
    this.result = result;
  }
}
