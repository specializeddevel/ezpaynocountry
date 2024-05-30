package com.ezpay.utils;

import com.ezpay.model.enums.CardType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CardDto {

    private Long id;
    private String fullName;
    private Long cvv;
    private CardType type;
    private Date creationDate;
    private Date expirationDate;
    private Long cardNumber;
    private boolean isActive;

}