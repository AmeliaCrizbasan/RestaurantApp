package com.example.restaurant.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuRequest {
    private String product;
    private Double price;
    private String details;
}
