package com.ilyaproject.order.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class OrderAlreadyExistsException extends RuntimeException{
    public OrderAlreadyExistsException(String message) {
        super(message);
    }
}
