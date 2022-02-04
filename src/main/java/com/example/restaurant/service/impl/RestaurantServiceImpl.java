package com.example.restaurant.service.impl;

import com.example.restaurant.DTO.*;
import com.example.restaurant.model.Menu;
import com.example.restaurant.model.Stock;
import com.example.restaurant.repository.RestaurantRepository;
import com.example.restaurant.repository.StockRepository;
import com.example.restaurant.service.RestaurantService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final StockRepository stockRepository;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository, StockRepository stockRepository) {
        this.restaurantRepository = restaurantRepository;
        this.stockRepository = stockRepository;
    }


    @Override
    public MessageResponse addMenu(MenuRequest request) {

        Menu menu = new Menu();
        MessageResponse responseMessage = new MessageResponse();
        menu.setProduct(request.getProduct());
        menu.setPrice(request.getPrice());
        menu.setDetails(request.getDetails());

        Optional<Menu> menu1 = restaurantRepository.findAllByProductAndAndPriceAndAndDetails(request.getProduct(),
                request.getPrice(), request.getDetails());

        if (menu1.isPresent()) {
            Optional<Stock> stock = stockRepository.findAllByMenu(menu1.get());
            if (stock.isPresent()) {
                Stock existingStock = stock.get();
                existingStock.setStock(existingStock.getStock() + 1);
                stockRepository.save(existingStock);

                responseMessage.setMessage(existingStock.getStock().toString());

            } else {
                Stock newStock = new Stock();
                newStock.setStock(1);
                newStock.setMenu(menu1.get());
                stockRepository.save(newStock);

                responseMessage.setMessage("The product is no longer available");
            }
        } else {
            Menu response = restaurantRepository.save(menu);
            Stock newStock = new Stock();
            newStock.setStock(1);
            newStock.setMenu(response);
            stockRepository.save(newStock);

            responseMessage.setMessage("Add product");
        }


        return responseMessage;

    }

    public List<StockResponse> checkStock() {
        Iterable<Stock> allStocks = stockRepository.findAll();
        List<StockResponse> stockResponses = new ArrayList<>();

        for (Stock s : allStocks) {
            StockResponse stockResponse = new StockResponse();
            stockResponse.setStock(s.getStock());
            stockResponse.setProduct(s.getMenu().getProduct());
            stockResponses.add(stockResponse);
        }
        return stockResponses;
    }

    @Override
    public MessageResponse buyProducts(BuyRequest request) {
        Optional<Menu> menu = restaurantRepository.findAllByProduct(request.getProduct());
        MessageResponse response = new MessageResponse();
        if (menu.isPresent()) {
            Optional<Stock> stock = stockRepository.findAllByMenu(menu.get());
            Stock existingStock = stock.get();
            if (stock.isPresent() && existingStock.getStock() >= request.getPieces()) {
                existingStock.setStock(existingStock.getStock() - request.getPieces());
                stockRepository.save(existingStock);
                response.setMessage("Buy");
            } else {
                response.setMessage("The productis no longer available, stock: " + existingStock.getStock().toString());
            }
        } else {
            response.setMessage("The product does not exist");
        }
        return response;
    }

    @Override
    public List<MenuResponse> checkProducts() {
        Iterable<Menu> allMenu = restaurantRepository.findAll();
        List<MenuResponse> menuResponses = new ArrayList<>();

        for (Menu m : allMenu) {
            MenuResponse menu = new MenuResponse();
            menu.setProduct(m.getProduct());
            menu.setPrice(m.getPrice());
            menu.setDetails(m.getDetails());
            menuResponses.add(menu);
        }
        return menuResponses;
    }

}
