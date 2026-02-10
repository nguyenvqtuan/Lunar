package com.example.lunar.validator;

import com.example.lunar.validator.impl.WalletRequestValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = WalletRequestValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidWalletRequest {

    String message() default "Invalid loan initialization request";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
