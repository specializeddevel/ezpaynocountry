package com.ezpay.service;

import com.ezpay.exceptions.UserNotFoundException;
import com.ezpay.model.entity.Account;
import com.ezpay.model.enums.AccountType;
import com.ezpay.repository.AccountRepository;
import com.ezpay.repository.UserRepository;
import com.ezpay.utils.AccountUtilities;
import com.ezpay.utils.dto.Account.AccountCreateDto;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService
{

    private final AccountRepository accountRepository;
    private final UserService userService;

    public AccountServiceImpl(AccountRepository accountRepository, UserRepository userRepository, UserService userService) {
        this.accountRepository = accountRepository;
        this.userService = userService;
    }

    @Override
    @Transactional
    public Account save(AccountCreateDto data) throws UserNotFoundException {
        userService.findUserById(data.userId())
                .orElseThrow(() -> new UserNotFoundException(data.userId()));

        Account account = Account.builder()
                .accountId(AccountUtilities.generateSecureAccountNumber())
                .accountType(AccountType.valueOf(data.accountType().getDescription().toUpperCase()))
                .cvu(AccountUtilities.generateCvuNumber())
                .dailyLimit(AccountUtilities.setLimitByAccountType(AccountType.valueOf(data.accountType().getDescription().toUpperCase())))
                .isActive(true)
                .balance(0D)
                .userId(data.userId())
                .build();

        return accountRepository.save(account);
    }

}
