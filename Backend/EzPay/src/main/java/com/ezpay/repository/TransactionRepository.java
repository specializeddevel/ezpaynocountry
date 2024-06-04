package com.ezpay.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ezpay.model.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {

}
