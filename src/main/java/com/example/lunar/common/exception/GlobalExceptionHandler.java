package com.example.lunar.common.exception;

import com.example.lunar.dto.response.ErrorResponse;
import com.example.lunar.enumration.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFoundException ex) {
        ErrorResponse errorResponse =
                new ErrorResponse(ErrorCode.RESOURCE_NOT_FOUND.getCode(), ex.getMessage(), ex.getResult());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorResponse> handleValidation(ValidationException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ErrorCode.VALIDATION.getCode(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_CONTENT).body(errorResponse);
    }

    @ExceptionHandler(IllegalException.class)
    public ResponseEntity<ErrorResponse> handleIllegal(IllegalException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ErrorCode.ILLEGAL.getCode(), ex.getMessage(), ex.getResult());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequest(BadRequestException ex) {
        ErrorResponse errorResponse =
                new ErrorResponse(ErrorCode.BAD_REQUEST.getCode(), ex.getMessage(), ex.getResult());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}
