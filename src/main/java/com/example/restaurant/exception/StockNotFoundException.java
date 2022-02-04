package com.example.restaurant.exception;

public class StockNotFoundException extends Exception{
    public StockNotFoundException(){
        super("Sould out!");
    }
}
