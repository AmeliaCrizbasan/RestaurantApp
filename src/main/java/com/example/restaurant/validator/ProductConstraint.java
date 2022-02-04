package com.example.restaurant.validator;

import com.example.restaurant.validator.impl.ProductValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ProductValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)

public @interface ProductConstraint {
    String message() default "Invalid product";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
