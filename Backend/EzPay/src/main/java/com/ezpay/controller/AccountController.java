package com.ezpay.controller;

import com.ezpay.model.entity.Account;
import com.ezpay.service.AccountService;
import com.ezpay.utils.dto.Account.AccountCreateDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Validated
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;


    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping()
    public ResponseEntity<?> createAccount(@Valid @RequestBody AccountCreateDto data)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.accountService.save(data));
    }

}
