package com.ezpay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.ezpay.model.entity.Transaction;
import com.ezpay.service.TransactionService;
import com.ezpay.utils.dto.Account.TransactionRequest;
import com.ezpay.utils.dto.Account.ErrorMessage;;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;
    
    @GetMapping("/all")
    public ResponseEntity<?> getAllTransactions() {
        try{
            return new ResponseEntity<>(transactionService.getAllTransaction(),HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(new ErrorMessage(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/withdraw")
    public ResponseEntity<?> withdraw(@RequestBody TransactionRequest transactionRequest  ){
        try{
            return new ResponseEntity<>(transactionService.withdraw(transactionRequest),HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(new ErrorMessage(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/deposit")
    public ResponseEntity<?> deposit(@RequestBody TransactionRequest transactionRequest ){
        try{
            return new ResponseEntity<>(transactionService.deposit(transactionRequest),HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(new ErrorMessage(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/transfer")
    public ResponseEntity<?> transferToDestinyAccount(@RequestBody TransactionRequest transactionRequest  ){
        try{
            return new ResponseEntity<>(transactionService.transfer(transactionRequest),HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(new ErrorMessage(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
    
    @GetMapping( "/{id}")
    public ResponseEntity<Transaction> getById(@PathVariable Integer id) {
        try{
            return new ResponseEntity<>(transactionService.getTransactionById(id),HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(new ErrorMessage(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

