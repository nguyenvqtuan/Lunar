package com.example.lunar.service.validation.implement;

import com.example.lunar.common.exception.ResourceNotFoundException;
import com.example.lunar.dto.command.WalletCommand;
import com.example.lunar.entity.Wallet;
import com.example.lunar.enumration.WalletStatus;
import com.example.lunar.repository.WalletRepository;
import com.example.lunar.service.validation.WalletValidationRule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WalletStatusRule implements WalletValidationRule<WalletCommand> {

    private final WalletRepository walletRepository;

    @Override
    public void validate(WalletCommand command) {
        Wallet wallet = walletRepository
                .findByUsername(command.userName())
                .orElseThrow(() -> new ResourceNotFoundException("Wallet not found"));

        if (WalletStatus.ACTIVE.getId().equals(wallet.getStatus())) {
            throw new RuntimeException("Wallet not active");
        }
    }
}
