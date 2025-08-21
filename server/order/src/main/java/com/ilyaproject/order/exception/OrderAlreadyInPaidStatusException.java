package com.ilyaproject.order.exception;

public class OrderAlreadyInPaidStatusException extends RuntimeException{
    public OrderAlreadyInPaidStatusException(String message) {
        super(message);
    }
}
