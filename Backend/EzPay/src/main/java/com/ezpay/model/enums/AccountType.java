package com.ezpay.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AccountType {
    PERSONAL("Personal"),
    BUSINESS("Business"),
    PLATINUM("Platinum");

    private final String description;
}
