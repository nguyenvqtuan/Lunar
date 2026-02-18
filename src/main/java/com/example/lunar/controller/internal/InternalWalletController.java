package com.example.lunar.controller.internal;

import com.example.lunar.dto.command.WalletCommand;
import com.example.lunar.dto.request.WalletRequest;
import com.example.lunar.dto.response.ResponseRestApi;
import com.example.lunar.service.internal.InternalWalletService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RequiredArgsConstructor
@RestController("/v1/internal/wallet")
public class InternalWalletController {

    private final InternalWalletService internalWalletService;

    @PostMapping("/validate")
    public ResponseRestApi<Void> validateWallet(@RequestBody @Valid WalletRequest walletRequest) {
        WalletCommand command = new WalletCommand(walletRequest.userName(), walletRequest.currency());
        internalWalletService.validateWallet(command);

        return ResponseRestApi.success();
    }
}
