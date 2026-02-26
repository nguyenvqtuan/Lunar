package com.example.lunar.service;

import com.example.lunar.dto.command.TransferCommand;
import com.example.lunar.dto.command.WalletCommand;
import com.example.lunar.dto.response.IdempotencyResult;
import com.example.lunar.dto.response.TransferResult;
import com.example.lunar.entity.Transaction;
import com.example.lunar.enumration.TransferStatus;
import com.example.lunar.helper.HashHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TransferService {

    private final IdempotencyService idempotencyService;
    private final WalletService walletService;
    private final LedgerService ledgerService;
    private final TransactionService transactionService;

    @Transactional
    public TransferResult transfer(TransferCommand command) {
        String requestHash = HashHelper.hash(command);
        IdempotencyResult idem = idempotencyService.checkOrCreate(command.idempotencyKey(), requestHash);
        if (idem.isCompleted()) {
            return transactionService.getById(idem.getTransactionId());
        }

        if (idem.isProcessing()) {
            throw new RuntimeException("Already processing");
        }

        try {
            WalletCommand walletCommand = WalletCommand.builder().build();
            walletService.validateTransfer(walletCommand);

            Transaction tx = transactionService.save(command, TransferStatus.PROCESSING);

            // IMPORTANT: always lock in deterministic order
            Long from = command.fromWalletId();
            Long to = command.toWalletId();

            if (from.compareTo(to) < 0) {
                walletService.debit(from, command.amount());
                walletService.credit(to, command.amount());
            } else {
                walletService.credit(to, command.amount());
                walletService.debit(from, command.amount());
            }

            ledgerService.createDebit(tx.getId(), from, command.amount());
            ledgerService.createCredit(tx.getId(), to, command.amount());

            transactionService.markCompleted(tx);
            idempotencyService.markCompleted(command.idempotencyKey(), tx.getId());

            return TransferResult.builder().transactionId(tx.getId().toString()).build();
        } catch (Exception ex) {
            idempotencyService.markFailed(command.idempotencyKey());
            throw ex;
        }
    }
}
