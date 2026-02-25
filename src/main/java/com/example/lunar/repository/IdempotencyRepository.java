package com.example.lunar.repository;

import com.example.lunar.entity.Idempotency;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IdempotencyRepository extends JpaRepository<Idempotency, Long> {

    Optional<Idempotency> findByIdempotencyKey(String key);
}
