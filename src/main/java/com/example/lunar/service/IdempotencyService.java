package com.example.lunar.service;

import com.example.lunar.dto.response.IdempotencyResult;
import com.example.lunar.entity.Idempotency;
import com.example.lunar.enumration.IdempotencyStatus;
import com.example.lunar.repository.IdempotencyRepository;
import java.time.Duration;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IdempotencyService {

    private final IdempotencyRepository idempotencyRepository;
    private static final Duration TTL = Duration.ofHours(24);

    public IdempotencyResult checkOrCreate(String key, String requestHash) {

        return idempotencyRepository
                .findByIdempotencyKey(key)
                .map(existing -> handleExisting(existing, requestHash))
                .orElseGet(() -> create(key, requestHash));
    }

    public void markCompleted(String key, Long transactionId) {
        Idempotency record = idempotencyRepository.findByIdempotencyKey(key).orElseThrow();

        if (IdempotencyStatus.COMPLETED.getId().equals(record.getStatus())) {
            return;
        }

        markCompleted(record, transactionId);
        idempotencyRepository.save(record);
    }

    public IdempotencyResult create(String key, String requestHash) {
        try {
            Idempotency record = init(key, requestHash);
            idempotencyRepository.save(record);

            return IdempotencyResult.acquired();
        } catch (DataIntegrityViolationException e) {
            // race condition fallback
            return idempotencyRepository
                    .findByIdempotencyKey(key)
                    .map(existing -> handleExisting(existing, requestHash))
                    .orElseThrow();
        }
    }

    private void markCompleted(Idempotency record, Long transactionId) {
        record.setTransactionId(transactionId);
        record.setStatus(IdempotencyStatus.COMPLETED.getId());
    }

    private Idempotency init(String key, String requestHash) {
        LocalDateTime currentDate = LocalDateTime.now();

        return Idempotency.builder()
                .idempotencyKey(key)
                .requestHash(requestHash)
                .status(IdempotencyStatus.PROCESSING.getId())
                .createdDate(currentDate)
                .expiresAt(currentDate.plus(TTL))
                .build();
    }

    private IdempotencyResult handleExisting(Idempotency record, String requestHash) {
        if (record.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Idempotency expired");
        }

        if (!record.getRequestHash().equals(requestHash)) {
            throw new RuntimeException("Same key but different payload");
        }

        if (IdempotencyStatus.COMPLETED.getId().equals(record.getStatus())) {
            return IdempotencyResult.completed(record.getTransactionId());
        }

        return IdempotencyResult.processing();
    }
}
