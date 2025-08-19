package com.ilyaproject.payment.exception;

public class PaymentDetailsValidationFailedException extends RuntimeException{
    public PaymentDetailsValidationFailedException(String message) {
        super(message);
    }
}
