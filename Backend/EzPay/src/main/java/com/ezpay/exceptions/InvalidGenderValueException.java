package com.ezpay.exceptions;

public class InvalidGenderValueException extends RuntimeException {
    public InvalidGenderValueException(String message, Throwable cause) {
        super(message, cause);
    }
}