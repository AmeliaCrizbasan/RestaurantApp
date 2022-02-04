package com.example.restaurant.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BuyRequest {
    private String product;
    private Integer pieces;
}
