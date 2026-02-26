package com.example.lunar.service;

import com.example.lunar.entity.Ledger;
import com.example.lunar.enumration.LedgerType;
import com.example.lunar.repository.LedgerRepository;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LedgerService {

    private final LedgerRepository ledgerRepository;

    public void createDebit(Long txId, Long walletId, BigDecimal amount) {

        Ledger entry = Ledger.builder()
                .transactionId(txId)
                .walletId(walletId)
                .type(LedgerType.DEBIT)
                .amount(amount)
                //                .balanceAfter(balanceAfter)
                .build();

        ledgerRepository.save(entry);
    }

    public void createCredit(Long txId, Long walletId, BigDecimal amount) {

        Ledger entry = Ledger.builder()
                .transactionId(txId)
                .walletId(walletId)
                .type(LedgerType.CREDIT)
                .amount(amount)
                //                .balanceAfter(balanceAfter)
                .build();

        ledgerRepository.save(entry);
    }
}
