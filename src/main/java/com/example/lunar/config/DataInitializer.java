package com.example.lunar.config;

import com.example.lunar.entity.Currency;
import com.example.lunar.entity.Wallet;
import com.example.lunar.repository.WalletRepository;
import jakarta.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DataInitializer implements CommandLineRunner {

    private final EntityManager entityManager;
    private final WalletRepository walletRepository;

    public DataInitializer(EntityManager entityManager, WalletRepository walletRepository) {
        this.entityManager = entityManager;
        this.walletRepository = walletRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        if (walletRepository.count() == 0) {
            // Initialize Currency
            Currency usd = new Currency();
            usd.setCurrency("USD");
            usd.setCreatedDate(LocalDateTime.now());
            usd.setCreatedBy("system");
            entityManager.persist(usd);

            Currency eur = new Currency();
            eur.setCurrency("EUR");
            eur.setCreatedDate(LocalDateTime.now());
            eur.setCreatedBy("system");
            entityManager.persist(eur);

            // Initialize Wallets
            Wallet user1Wallet = new Wallet();
            user1Wallet.setUserName("user1");
            user1Wallet.setCurrencyId(usd.getId());
            user1Wallet.setStatus("ACTIVE");
            user1Wallet.setBalance(new BigDecimal("1000.00"));
            user1Wallet.setCreatedDate(LocalDateTime.now());
            user1Wallet.setCreatedBy("system");
            walletRepository.save(user1Wallet);

            Wallet user2Wallet = new Wallet();
            user2Wallet.setUserName("user2");
            user2Wallet.setCurrencyId(usd.getId());
            user2Wallet.setStatus("ACTIVE");
            user2Wallet.setBalance(new BigDecimal("500.00"));
            user2Wallet.setCreatedDate(LocalDateTime.now());
            user2Wallet.setCreatedBy("system");
            walletRepository.save(user2Wallet);

            System.out.println("====== DATA INITIALIZED ======");
            System.out.println(
                    "Wallet 1 (user1): ID = " + user1Wallet.getId() + ", Balance = " + user1Wallet.getBalance());
            System.out.println(
                    "Wallet 2 (user2): ID = " + user2Wallet.getId() + ", Balance = " + user2Wallet.getBalance());
            System.out.println("==============================");
        }
    }
}
