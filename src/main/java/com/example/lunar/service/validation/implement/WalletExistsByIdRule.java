package com.example.lunar.service.validation.implement;

import com.example.lunar.common.exception.ResourceNotFoundException;
import com.example.lunar.dto.command.WalletCommand;
import com.example.lunar.repository.WalletRepository;
import com.example.lunar.service.validation.WalletValidationRule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WalletExistsByIdRule implements WalletValidationRule<WalletCommand> {

    private final WalletRepository walletRepository;

    @Override
    public void validate(WalletCommand command) {
        walletRepository
                .findById(command.fromWalletId())
                .orElseThrow(() -> new ResourceNotFoundException("From wallet not found"));

        walletRepository
                .findById(command.toWalletId())
                .orElseThrow(() -> new ResourceNotFoundException("To wallet not found"));
    }
}
