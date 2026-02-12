package com.example.lunar.repository;

import com.example.lunar.entity.Wallet;
import io.lettuce.core.dynamic.annotation.Param;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface WalletRepository extends JpaRepository<Wallet, UUID> {

    @Query("SELECT w FROM Wallet WHERE w.id = :id and w.deletedDate IS NULL")
    Optional<Wallet> findByIdWithoutDeleted(@Param("id") UUID id);

    @Query("SELECT w FROM Wallet w INNER JOIN Currency c ON w.currencyId = c.id WHERE c.currency = :currency and w.deletedDate IS NULL")
    List<Wallet> findByCurrency(@Param("currency") String currency);

    @Query("SELECT w FROM Wallet w INNER JOIN Currency c ON w.currencyId = c.id WHERE c.currency = :currency AND w.deletedDate IS NULL AND w.userName = :userName")
    Optional<Wallet> findByCurrencyAndUser(@Param("currency") String currency, @Param("user") String userName);
}
