package com.example.lunar.repository;

import com.example.lunar.entity.Wallet;
import io.lettuce.core.dynamic.annotation.Param;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface WalletRepository extends JpaRepository<Wallet, Long> {

    @Query("SELECT w FROM Wallet w WHERE w.id = :id and w.deletedDate IS NULL")
    Optional<Wallet> findByIdWithoutDeleted(@Param("id") Long id);

    @Query("SELECT w FROM Wallet w WHERE w.userName = :username and w.deletedDate IS NULL")
    Optional<Wallet> findByUsername(@Param("username") String username);

    @Query(
            "SELECT w FROM Wallet w INNER JOIN Currency c ON w.currencyId = c.id WHERE c.currency = :currency and w.deletedDate IS NULL")
    List<Wallet> findByCurrency(@Param("currency") String currency);

    @Query(
            "SELECT w FROM Wallet w INNER JOIN Currency c ON w.currencyId = c.id WHERE c.currency = :currency AND w.deletedDate IS NULL AND w.userName = :userName")
    Optional<Wallet> findByCurrencyAndUser(@Param("currency") String currency, @Param("user") String userName);

    @Modifying
    @Query(
            """
        UPDATE Wallet w
        SET w.balance = w.balance - :amount,
            w.updatedDate = CURRENT_TIMESTAMP
        WHERE w.id = :walletId
          AND w.status = 'ACTIVE'
          AND w.balance >= :amount
    """)
    int atomicDebit(@Param("walletId") Long walletId, @Param("amount") BigDecimal amount);

    @Modifying
    @Query(
            """
        UPDATE Wallet w
        SET w.balance = w.balance + :amount,
            w.updatedDate = CURRENT_TIMESTAMP
        WHERE w.id = :walletId
          AND w.status = 'ACTIVE'
    """)
    int atomicCredit(@Param("walletId") Long walletId, @Param("amount") BigDecimal amount);
}
