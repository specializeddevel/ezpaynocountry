package com.ezpay.utils.dto.Account;

import com.ezpay.model.entity.Account;

import java.util.function.Function;

public class AccountCreateDtoMapper implements Function<Account, AccountCreateDto> {

    @Override
    public AccountCreateDto apply(Account account) {

        return new AccountCreateDto(
                account.getAccountNumber(),
                account.getCvu(),
                account.getAccountType().getDescription(),
                account.getUserId());
    }
}
