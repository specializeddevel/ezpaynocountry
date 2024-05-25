package com.ezpay.service;

import com.ezpay.model.entity.Account;
import com.ezpay.utils.dto.Account.AccountCreateDto;
import org.springframework.stereotype.Service;


public interface AccountService {

    Account save(AccountCreateDto accountCreateDto);

}
