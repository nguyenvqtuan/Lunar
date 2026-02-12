package com.example.lunar.service.internal;

import com.example.lunar.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class InternalWalletService {

    private final WalletRepository walletRepository;

    private void validateWallet() {

    }
}
