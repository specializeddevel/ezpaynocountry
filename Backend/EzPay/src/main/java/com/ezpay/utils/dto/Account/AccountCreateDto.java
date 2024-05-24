package com.ezpay.utils.dto.Account;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.With;

@With
public record AccountCreateDto(
    @NotNull(message = "Account number cannot be null.")
    @NotBlank(message = "Account number cannot be blank.")
    String accountNumber,

    @NotNull(message = "CVU cannot be null.")
    @NotBlank(message = "CVU cannot be blank.")
    String cvu,

    @NotNull(message = "Account type cannot be null.")
    @NotBlank(message = "Account type cannot be blank.")
    String accountType,

    @NotNull @Min(message = "User ID cannot be less than 1.", value = 1)
    Long userId
)
{

}
