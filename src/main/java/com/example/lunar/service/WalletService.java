package com.example.lunar.service;

import com.example.lunar.dto.command.WalletCommand;
import com.example.lunar.repository.WalletRepository;
import com.example.lunar.service.validation.implement.CreateWalletValidationStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class WalletService {

    private final CreateWalletValidationStrategy createStrategy;
    private final WalletRepository walletRepository;

    public void createWallet(WalletCommand command) {
        createStrategy.validate(command);
    }

    public void getWallet() {}
}
