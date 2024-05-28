package com.ezpay.exceptions;

public class UnderAgeException extends RuntimeException
{
    public static String UNDER_AGE_MESSSAGE = "Minimun age for open accounts is 18 years, actual age is: ";

    public UnderAgeException(Integer years) {
        super(UNDER_AGE_MESSSAGE + years);
    }

}

