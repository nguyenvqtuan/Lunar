package com.example.lunar.service.validation;

public interface WalletValidationStrategy<T> {

    void validate(T command);
}
