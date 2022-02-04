package com.example.restaurant.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ErrorHandle extends ResponseEntityExceptionHandler {
    @ExceptionHandler({StockNotFoundException.class})
    protected ResponseEntity<ApiError> handleNotFound(Exception ex) {
        ApiError apiError = new ApiError();
        apiError.setMessage(ex.getMessage());
        apiError.setStatus(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }


}
