package com.example.lunar.dto.mapper;

import com.example.lunar.dto.command.WalletCommand;
import com.example.lunar.dto.request.WalletRequest;
import com.example.lunar.dto.response.WalletResponse;
import com.example.lunar.entity.Wallet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WalletMapper {

    WalletResponse toDto(Wallet wallet);

    @Mapping(source = "userName", target = "userName")
    @Mapping(source = "currency", target = "currency")
    WalletCommand from(WalletRequest request);
}
