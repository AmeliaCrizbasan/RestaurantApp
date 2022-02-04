package com.example.restaurant.validator.impl;

import com.example.restaurant.validator.ProductConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ProductValidator implements ConstraintValidator<ProductConstraint, String> {

    @Override
    public boolean isValid(String productField, ConstraintValidatorContext ctx) {
        return productField != null;
    }
}
