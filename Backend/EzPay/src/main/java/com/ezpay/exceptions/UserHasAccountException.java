package com.ezpay.exceptions;

public class UserHasAccountException extends RuntimeException
{
    public static String USER_HASACCOUNT = "Account already registered for user ID: ";

    public UserHasAccountException(Integer id) {
        super(USER_HASACCOUNT + id);
    }

}

