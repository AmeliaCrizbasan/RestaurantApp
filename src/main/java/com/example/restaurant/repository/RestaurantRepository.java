package com.example.restaurant.repository;

import com.example.restaurant.model.Menu;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestaurantRepository extends CrudRepository<Menu, Integer> {
public Optional<Menu> findAllByProductAndAndPriceAndAndDetails(String product, Double price, String details);
public Optional<Menu>findAllByProduct(String product);
}
