package com.ezpay.utils;

import com.ezpay.model.enums.AccountType;

import java.security.SecureRandom;

public class AccountUtilities {

    private static final SecureRandom secureRandom = new SecureRandom();

    public static String generateSecureAccountNumber() {
        // Genera un número de cuenta aleatorio de 10 dígitos
        StringBuilder accountNumber = new StringBuilder("ACC-");
        for (int i = 0; i < 10; i++) {
            int digit = secureRandom.nextInt(10); // Genera un dígito entre 0 y 9
            accountNumber.append(digit);
        }
        return accountNumber.toString();
    }

    public static String generateCvuNumber() {
        // Genera un número de CVU aleatorio de 15 dígitos
        StringBuilder accountNumber = new StringBuilder();
        for (int i = 0; i < 15; i++) {
            int digit = secureRandom.nextInt(10); // Genera un dígito entre 0 y 9
            accountNumber.append(digit);
        }
        return accountNumber.toString();
    }

    public static Double setLimitByAccountType(AccountType accountType)
    {
        final Double PERSONAL_LIMIT = 50_000D;
        final Double BUSINESS_LIMIT = 200_000D;
        final Double PLATINUM_LIMIT = 1_000_000D;

        switch (accountType) {
            case PERSONAL -> {
                return PERSONAL_LIMIT;
            }
            case BUSINESS -> {
                return BUSINESS_LIMIT;
            }
            case PLATINUM -> {
                return PLATINUM_LIMIT;
            }
        }
        return 0D;
    }
}

