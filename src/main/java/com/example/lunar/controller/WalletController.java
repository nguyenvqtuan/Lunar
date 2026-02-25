package com.example.lunar.controller;

import com.example.lunar.dto.command.WalletCommand;
import com.example.lunar.dto.mapper.WalletMapper;
import com.example.lunar.dto.request.WalletRequest;
import com.example.lunar.dto.response.ResponseRestApi;
import com.example.lunar.dto.response.WalletResponse;
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
    private final WalletMapper walletMapper;

    @PostMapping
    public ResponseRestApi<Boolean> createWallet(@RequestBody @Valid WalletRequest walletRequest) {
        WalletCommand command = walletMapper.from(walletRequest);
        walletService.createWallet(command);

        return ResponseRestApi.success(Boolean.FALSE);
    }

    @GetMapping
    public ResponseRestApi<WalletResponse> getWallet(@RequestBody @Valid WalletRequest walletRequest) {
        WalletCommand command = walletMapper.from(walletRequest);
        WalletResponse walletResponse = walletService.getWallet(command);

        return ResponseRestApi.success(walletResponse);
    }
}
