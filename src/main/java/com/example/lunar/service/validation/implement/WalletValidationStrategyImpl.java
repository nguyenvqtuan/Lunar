package com.example.lunar.service.validation.implement;

import com.example.lunar.service.validation.WalletValidationRule;
import com.example.lunar.service.validation.WalletValidationStrategy;
import java.util.List;

public abstract class WalletValidationStrategyImpl<T> implements WalletValidationStrategy<T> {

    private final List<WalletValidationRule<T>> rules;

    protected WalletValidationStrategyImpl(List<WalletValidationRule<T>> rules) {
        this.rules = rules;
    }

    @Override
    public void validate(T command) {
        for (WalletValidationRule<T> rule : rules) {
            rule.validate(command);
        }
    }
}
