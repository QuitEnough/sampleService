package com.sysco.sampleService.validation;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.util.Arrays.asList;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ValidSellerId.SellerIdValidator.class})
public @interface ValidSellerId {

    String message() default "Can be only USBL or CABL";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};


    class SellerIdValidator implements ConstraintValidator<ValidSellerId, String> {
        @Override
        public boolean isValid(String sellerId, ConstraintValidatorContext context) {
            if (sellerId == null) {
                return false;
            }
            return asList("USBL", "CABL").contains(sellerId.toUpperCase());
        }


    }
}
