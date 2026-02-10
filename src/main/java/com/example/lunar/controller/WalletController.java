package com.example.lunar.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/v1/wallet")
public class WalletController {
    @PostMapping
    public StatusResponse createWallet() {

    }

    @GetMapping
    public StatusResponse getWallet() {

    }
}
