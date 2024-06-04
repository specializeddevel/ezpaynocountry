package com.ezpay.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezpay.exceptions.TransactionNotFoundException;
import com.ezpay.model.entity.Account;
import com.ezpay.model.entity.Transaction;
import com.ezpay.model.enums.TransactionType;
import com.ezpay.repository.AccountRepository;
import com.ezpay.repository.TransactionRepository;
import com.ezpay.utils.dto.Account.TransactionRequest;
import com.ezpay.exceptions.AccountNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class TransactionService{

    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    AccountRepository accountRepository;

    public List<Transaction>getAllTransaction(){
        return transactionRepository.findAll();
    }

    public Transaction setDataTransaction(TransactionRequest transactionRequest) {
        Transaction transactionSave = new Transaction();
        Integer idAccount = transactionRequest.getOriginAccount();
    
        // Validar si la cuenta existe
        Account originAccount = accountRepository.findByUserId(idAccount)
                .orElseThrow(() -> new AccountNotFoundException(idAccount));
    
        transactionSave.setAccount(originAccount);
        transactionSave.setAmount(transactionRequest.getAmount());
        transactionSave.setDescription(transactionRequest.getDescription());
        transactionSave.setDate(LocalDateTime.now());
    
        return transactionSave;
    }
    
    @Transactional
    public Transaction deposit(TransactionRequest transactionRequest) {
        // Crear la transacción usando setDataTransaction
        Transaction depositTransaction = setDataTransaction(transactionRequest);
        depositTransaction.setType(TransactionType.DEPOSIT);

        // Obtener la cuenta de origen
        Account originAccount = depositTransaction.getAccount();
        Double balance = originAccount.getBalance();
        Double amount = transactionRequest.getAmount();

        //Validar el monto minimo
        if(amount<=0)throw new InvalidAmountException("El monto minimo de deposito es de 5 dolares");
        // Actualizar el balance de la cuenta
        originAccount.setBalance(balance + amount);

        // Guardar la cuenta actualizada
        accountRepository.save(originAccount);

        // Guardar y retornar la transacción
        return transactionRepository.save(depositTransaction);
    }
    @Transactional
    public Transaction transfer(TransactionRequest transactionRequest) throws InsufficientFundsException {
        Transaction depositTransaction = setDataTransaction(transactionRequest);
        depositTransaction.setType(TransactionType.TRANSFER);
        Integer idAccount = transactionRequest.getDestinyAccount();

        // Validación de cuentas
        Account originAccount = depositTransaction.getAccount();
        Account destinyAccount = accountRepository.findByUserId(idAccount)
                .orElseThrow(() -> new AccountNotFoundException(idAccount));
        depositTransaction.setDestinyAccount(destinyAccount);        

        // Validación del monto respecto del saldo
        Double amount = depositTransaction.getAmount();
        Double balanceOrigin = originAccount.getBalance();

        if(amount<=0)throw new InvalidAmountException("El monto minimo de transferencia es de 5 dolares");

        // Verifica si la cuenta de origen tiene suficiente saldo
        if (amount > balanceOrigin) {
            throw new InsufficientFundsException("Insufficient funds in the origin account");
        }

        // Actualiza los balances de las cuentas
        originAccount.setBalance(balanceOrigin - amount);
        destinyAccount.setBalance(destinyAccount.getBalance() + amount);

        // Guarda las cuentas actualizadas
        accountRepository.save(originAccount);
        accountRepository.save(destinyAccount);

        // Guarda y retorna la transacción
        return transactionRepository.save(depositTransaction);
    }
    public Transaction withdraw(TransactionRequest transactionRequest) throws InsufficientFundsException {
        // Crear la transacción usando setDataTransaction
        Transaction withdrawTransaction = setDataTransaction(transactionRequest);
        withdrawTransaction.setType(TransactionType.WITHDRAW);
    
        // Obtener la cuenta de origen
        Account originAccount = withdrawTransaction.getAccount();
        Double balance = originAccount.getBalance();
        Double amount = transactionRequest.getAmount();

        if(amount<=0)throw new InvalidAmountException("El monto minimo de retiro es de 5 dolares");
    
        // Verificar si la cuenta tiene saldo suficiente
        if (amount > balance) {
            throw new InsufficientFundsException("Insufficient funds in the account");
        }
    
        // Actualizar el balance de la cuenta
        originAccount.setBalance(balance - amount);
    
        // Guardar la cuenta actualizada
        accountRepository.save(originAccount);
    
        // Guardar y retornar la transacción
        return transactionRepository.save(withdrawTransaction);
    }
    public Transaction getTransactionById(Integer id){
        return transactionRepository.findById(id.longValue()).orElseThrow(()->new TransactionNotFoundException(id));
    }

    public class InsufficientFundsException extends RuntimeException {
        public InsufficientFundsException(String message) {
            super(message);
        }
    }

    public class InvalidAmountException extends RuntimeException {
        public InvalidAmountException(String message) {
            super(message);
        }
    }
    
}
