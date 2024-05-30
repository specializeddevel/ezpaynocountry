package com.ezpay.repository;


import com.ezpay.model.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card,Long> {

//    @Query("SELECT * FROM ezpay.card WHERE is_active = TRUE")
//    List<Card> findActiveCards();



}
