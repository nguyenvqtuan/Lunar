package com.example.lunar.service.validation;

public interface WalletValidationRule<T> {

    void validate(T command);
}
