package com.ezpay.utils.dto.Account;

import com.ezpay.model.enums.TransactionType;

import lombok.Data;

@Data
public class TransactionRequest {

    private Integer originAccount;
    private Integer destinyAccount;
    private Double amount;
    private String description; 
}
