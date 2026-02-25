package com.example.lunar.service;

import com.example.lunar.common.exception.ResourceNotFoundException;
import com.example.lunar.dto.command.WalletCommand;
import com.example.lunar.dto.mapper.WalletMapper;
import com.example.lunar.dto.response.WalletResponse;
import com.example.lunar.entity.Wallet;
import com.example.lunar.repository.WalletRepository;
import com.example.lunar.service.validation.implement.CreateWalletValidationStrategy;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Log4j2
public class WalletService {

    private final CreateWalletValidationStrategy createStrategy;
    private final WalletRepository walletRepository;
    private final WalletMapper walletMapper;

    public void createWallet(WalletCommand command) {
        createStrategy.validate(command);
    }

    public WalletResponse getWallet(WalletCommand command) {
        Wallet wallet = walletRepository
                .findByUsername(command.userName())
                .orElseThrow(() -> new ResourceNotFoundException("Wallet not found"));

        return walletMapper.toDto(wallet);
    }

    public void validateTransfer(WalletCommand command) {
        if (command.fromWalletId().equals(command.toWalletId())) {
            throw new RuntimeException("Cannot transfer to same wallet");
        }

        if (command.amount() == null || command.amount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Invalid amount");
        }
    }

    @Transactional
    public void credit(Long walletId, BigDecimal amount) {
        int updated = walletRepository.atomicCredit(walletId, amount);

        if (updated == 0) {
            throw new RuntimeException("Credit failed (wallet not active)");
        }
    }

    @Transactional
    public void debit(Long walletId, BigDecimal amount) {
        int updated = walletRepository.atomicDebit(walletId, amount);

        if (updated == 0) {
            throw new RuntimeException("Debit failed (wallet not active or insufficient balance)");
        }
    }
}
