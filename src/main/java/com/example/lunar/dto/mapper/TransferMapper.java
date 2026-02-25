package com.example.lunar.dto.mapper;

import com.example.lunar.dto.command.TransferCommand;
import com.example.lunar.dto.request.TransferRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransferMapper {

    @Mapping(source = "request.amount", target = "amount")
    @Mapping(source = "request.toWalletId", target = "toWalletId")
    @Mapping(source = "request.fromWalletId", target = "fromWalletId")
    @Mapping(source = "idempotencyKey", target = "idempotencyKey")
    TransferCommand from(String idempotencyKey, TransferRequest request);
}
