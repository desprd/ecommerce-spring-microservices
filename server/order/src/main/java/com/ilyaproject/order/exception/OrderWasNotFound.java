package com.ilyaproject.order.exception;

public class OrderWasNotFound extends RuntimeException{
    public OrderWasNotFound(String message) {
        super(message);
    }
}
