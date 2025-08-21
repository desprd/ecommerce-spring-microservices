package com.ilyaproject.order.exception;

public class FailedToUpdateOrderStatusException extends RuntimeException{
    public FailedToUpdateOrderStatusException(String message) {
        super(message);
    }
}
