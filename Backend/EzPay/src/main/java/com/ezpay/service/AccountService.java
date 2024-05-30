package com.ezpay.service;

import com.ezpay.model.entity.Account;
import com.ezpay.utils.dto.Account.AccountCreateDto;
import org.springframework.stereotype.Service;

import java.util.Optional;


public interface AccountService {

    Account save(AccountCreateDto accountCreateDto) throws Exception;
    Optional<Account> existByUserId(Integer userId);

}
