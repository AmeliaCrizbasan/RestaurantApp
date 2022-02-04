package com.example.restaurant.service;

import com.example.restaurant.DTO.*;

import java.util.List;

public interface RestaurantService {
    MessageResponse addMenu(MenuRequest request);
   List<StockResponse> checkStock();
   MessageResponse buyProducts(BuyRequest request);
   List<MenuResponse> checkProducts();
}
