package com.ezpay.service;

import com.ezpay.model.entity.Account;
import com.ezpay.model.enums.AccountType;
import com.ezpay.repository.AccountRepository;
import com.ezpay.utils.AccountUtilities;
import com.ezpay.utils.dto.Account.AccountCreateDto;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService
{

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account save(AccountCreateDto data) {

        Account account = Account.builder()
                .accountId(AccountUtilities.generateSecureAccountNumber())
                .accountType(AccountType.valueOf(data.accountType().getDescription().toUpperCase()))
                .cvu(AccountUtilities.generateCvuNumber())
                .dailyLimit(AccountUtilities.setLimitByAccountType(AccountType.valueOf(data.accountType().getDescription().toUpperCase())))
                .userId(data.userId())
                .active(true)
                .balance(0D)
                .build();
        return accountRepository.save(account);
    }

}
