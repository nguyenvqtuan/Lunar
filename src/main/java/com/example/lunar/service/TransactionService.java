package com.example.lunar.service;

import com.example.lunar.common.exception.ResourceNotFoundException;
import com.example.lunar.dto.command.TransferCommand;
import com.example.lunar.dto.response.TransferResult;
import com.example.lunar.entity.Transaction;
import com.example.lunar.enumration.TransferStatus;
import com.example.lunar.repository.TransactionRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;

    @Transactional
    public Transaction save(TransferCommand cmd, TransferStatus status) {
        Transaction tx = new Transaction();
        tx.setFromWalletId(cmd.fromWalletId());
        tx.setToWalletId(cmd.toWalletId());
        tx.setAmount(cmd.amount());
        tx.setStatus(status.getId());
        tx.setCreatedDate(LocalDateTime.now());
        transactionRepository.save(tx);

        return tx;
    }

    @Transactional
    public Transaction markCompleted(Transaction tx) {
        tx.setStatus(TransferStatus.COMPLETED.getId());
        tx.setUpdatedDate(LocalDateTime.now());

        return transactionRepository.save(tx);
    }

    public TransferResult getById(Long txId) {
        Transaction tx = transactionRepository
                .findById(txId)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found"));

        return TransferResult.builder().transactionId(tx.getId().toString()).build();
    }
}
