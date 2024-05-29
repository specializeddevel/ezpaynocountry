package com.ezpay.model.entity;

import com.ezpay.model.enums.CardType;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table (name = "card")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name")
    private String fullName;
    @Column(name = "cvv")
    private Long cvv;

    @Enumerated(EnumType.STRING)
    @Column(name = "card_type")
    private CardType type;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "America/Mexico_City")
    @Column(name = "creation_date")
    private Date creationDate;
    @Column(name = "expiration_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "America/Mexico_City")
    private Date expirationDate;

    @Column(name = "card_number")
    private Long cardNumber;

    @Column(name = "is_active")
    private boolean isActive;

}