package com.example.lunar.service.validation.implement;

import com.example.lunar.common.exception.ValidationException;
import com.example.lunar.dto.command.WalletCommand;
import com.example.lunar.service.validation.WalletValidationRule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SufficientBalanceRule implements WalletValidationRule<WalletCommand> {

    @Override
    public void validate(WalletCommand walletBalance) {
        if (walletBalance.originalBalance().compareTo(walletBalance.movementBalance()) < 0) {
            throw new ValidationException("Insufficient balance");
        }
    }
}
