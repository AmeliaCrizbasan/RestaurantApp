package com.example.restaurant.repository;

import com.example.restaurant.model.Menu;
import com.example.restaurant.model.Stock;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface StockRepository extends CrudRepository<Stock, Integer> {
  public Optional<Stock> findAllByMenu(Menu menu);
}
