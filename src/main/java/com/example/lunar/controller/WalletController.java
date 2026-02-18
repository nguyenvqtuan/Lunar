package com.example.lunar.controller;

import com.example.lunar.dto.command.WalletCommand;
import com.example.lunar.dto.request.WalletRequest;
import com.example.lunar.dto.response.ResponseRestApi;
import com.example.lunar.service.WalletService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RequiredArgsConstructor
@RestController("/v1/wallet")
public class WalletController {

    private final WalletService walletService;

    @PostMapping()
    public ResponseRestApi<Boolean> createWallet(@RequestBody @Valid WalletRequest walletRequest) {
        WalletCommand command = new WalletCommand(walletRequest.userName(), walletRequest.currency());
        walletService.createWallet(command);

        return ResponseRestApi.success(Boolean.FALSE);
    }

    @GetMapping
    public ResponseRestApi<Void> getWallet() {

        return ResponseRestApi.success();
    }
}
