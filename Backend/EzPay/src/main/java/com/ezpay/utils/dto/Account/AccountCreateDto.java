package com.ezpay.utils.dto.Account;

import com.ezpay.model.enums.AccountType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record AccountCreateDto(
    @NotNull
    //@JsonDeserialize(using = AccountTypeDeserializer.class)
    AccountType accountType,

    @NotNull
    @Min(value = 1)
    Long userId
)
{

}
