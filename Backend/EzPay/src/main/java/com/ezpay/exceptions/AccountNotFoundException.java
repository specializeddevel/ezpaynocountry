package com.ezpay.exceptions;

public class AccountNotFoundException extends RuntimeException
{
    public static String ACCOUNT_NOT_EXISTS_BY_ID = "Cannot found account with ID: ";

    public AccountNotFoundException(Integer id) {
        super(ACCOUNT_NOT_EXISTS_BY_ID + id);
    }

}

