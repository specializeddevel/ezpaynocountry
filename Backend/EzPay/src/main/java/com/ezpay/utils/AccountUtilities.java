package com.ezpay.utils;

import com.ezpay.model.enums.AccountType;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.function.Consumer;

@Component
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

    public static Double setLimitByAccountType(AccountType accountType) {
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

    @Getter
    public static class Age {
        int years;
        int months;
        int days;

        public Age(int years, int months, int days) {
            this.years = years;
            this.months = months;
            this.days = days;
        }
    }

    public static Age calculateAge(LocalDate birthDate) {
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(birthDate, currentDate);

        return new Age(period.getYears(), period.getMonths(), period.getDays());
    }


    public static Consumer<Date> formatDate = date -> {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        System.out.println(formatter.format(date));
    };
}

