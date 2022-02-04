package com.example.restaurant.controller;

import com.example.restaurant.DTO.*;
import com.example.restaurant.exception.StockNotFoundException;
import com.example.restaurant.service.RestaurantService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(path = "/restaurant")
@AllArgsConstructor
public class RestaurantController {
    private final RestaurantService restaurantService;

    @PostMapping(path = "/add")
    public ResponseEntity<MessageResponse> addMenu(@RequestBody MenuRequest request) {
        return new ResponseEntity<>(restaurantService.addMenu(request), HttpStatus.OK);
    }

    @GetMapping(path = "/stock")
    public ResponseEntity<List<StockResponse>> checkStock() {
        return new ResponseEntity<>(restaurantService.checkStock(), HttpStatus.OK);
    }

    @PostMapping(path = "/buy")
    public ResponseEntity<MessageResponse> buyProduct(@RequestBody BuyRequest request) throws StockNotFoundException {
        MessageResponse response = restaurantService.buyProducts(request);
        if (response.getMessage().equals("Buy")) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            throw new StockNotFoundException();
        }
    }

    @GetMapping(path = "/checkProducts")
    public ResponseEntity<List<MenuResponse>> checkProducts() {
        return new ResponseEntity<>(restaurantService.checkProducts(), HttpStatus.OK);
    }

}
