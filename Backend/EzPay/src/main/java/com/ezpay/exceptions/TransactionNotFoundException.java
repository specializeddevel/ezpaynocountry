package com.ezpay.exceptions;

public class TransactionNotFoundException extends RuntimeException{

    public static String TRANSACTION_NOT_EXISTS_BY_ID = "Cannot found Transaction with ID: ";

    public TransactionNotFoundException(Integer id) {
        super(TRANSACTION_NOT_EXISTS_BY_ID + id);
    }
}
