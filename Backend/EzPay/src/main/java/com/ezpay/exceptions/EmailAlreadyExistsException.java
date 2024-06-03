package com.ezpay.exceptions;

public class EmailAlreadyExistsException extends RuntimeException {
    public static String EMAIL_ALREADY_EXISTS = "This email is already registered -> ";

    public EmailAlreadyExistsException(String email) {
        super(EMAIL_ALREADY_EXISTS + email);
    }

}