package com.example.lunar.service.internal;

import com.example.lunar.dto.command.WalletCommand;
import com.example.lunar.repository.WalletRepository;
import com.example.lunar.service.validation.implement.ValidateWalletValidationStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class InternalWalletService {

    private final ValidateWalletValidationStrategy validateStrategy;
    private final WalletRepository walletRepository;

    public void validateWallet(WalletCommand walletCommand) {
        validateStrategy.validate(walletCommand);
    }
}
