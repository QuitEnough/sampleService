package com.sysco.sampleService.validation;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;


import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.regex.Pattern;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Constraint(validatedBy = {ValidProductId.ProductIdValidator.class })
public @interface ValidProductId {

    String message() default "Could be just numbers";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };


    class ProductIdValidator implements ConstraintValidator<ValidProductId, String> {

        private static final Pattern VALID_PATTERN = Pattern.compile("^[0-9]+$");

        @Override
        public boolean isValid(String productId, ConstraintValidatorContext context) {

            if (productId == null) {
                return false;
            }

            return VALID_PATTERN.matcher(productId).matches();
        }
    }
}
