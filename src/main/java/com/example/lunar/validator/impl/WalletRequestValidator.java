package com.example.lunar.validator.impl;

import com.example.lunar.dto.request.WalletRequest;
import com.example.lunar.validator.ValidWalletRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class WalletRequestValidator implements ConstraintValidator<ValidWalletRequest, WalletRequest> {
    @Override
    public boolean isValid(WalletRequest value, ConstraintValidatorContext context) {
        return false;
    }
}
