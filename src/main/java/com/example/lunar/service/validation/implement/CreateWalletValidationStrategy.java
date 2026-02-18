package com.example.lunar.service.validation.implement;

import com.example.lunar.dto.command.WalletCommand;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class CreateWalletValidationStrategy extends WalletValidationStrategyImpl<WalletCommand> {

    public CreateWalletValidationStrategy(WalletExistsRule existsRule, WalletStatusRule statusRule) {

        super(List.of(existsRule, statusRule));
    }
}
