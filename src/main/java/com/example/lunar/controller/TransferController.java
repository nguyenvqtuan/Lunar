package com.example.lunar.controller;

import com.example.lunar.dto.command.TransferCommand;
import com.example.lunar.dto.mapper.TransferMapper;
import com.example.lunar.dto.request.TransferRequest;
import com.example.lunar.dto.response.TransferResponse;
import com.example.lunar.dto.response.TransferResult;
import com.example.lunar.service.TransferService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/transfers")
@RequiredArgsConstructor
@Slf4j
public class TransferController {

    private final TransferService transferService;
    private final TransferMapper transferMapper;

    @PostMapping
    public ResponseEntity<TransferResponse> transfer(
            @RequestHeader("Idempotency-Key") String idempotencyKey, @Valid @RequestBody TransferRequest request) {
        TransferCommand command = transferMapper.from(idempotencyKey, request);
        TransferResult result = transferService.transfer(command);

        TransferResponse response = TransferResponse.builder()
                .transactionId(result.getTransactionId())
                .status(result.getStatus())
                //                .amount(result.getAmount())
                //                .fromWalletId(result.getFromWalletId())
                //                .toWalletId(result.getToWalletId())
                .build();

        return ResponseEntity.ok(response);
    }
}
