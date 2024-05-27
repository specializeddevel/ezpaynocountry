package com.ezpay.exceptions;

public class UserNotFoundException extends RuntimeException
{
    public static String USER_NOT_EXISTS_BY_ID = "Cannot found user with ID: ";
    public static String USER_NOT_EXISTS_BY_EMAIL = "Cannot found user with email: ";

    public UserNotFoundException(Integer id) {
        super(USER_NOT_EXISTS_BY_ID + id);
    }
    public UserNotFoundException(String email) {
        super(USER_NOT_EXISTS_BY_EMAIL + email);
    }

}

