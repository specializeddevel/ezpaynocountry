package com.ezpay.model.entity;

import com.ezpay.model.enums.AccountType;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.Date;

@Builder
@Entity
@AllArgsConstructor
@Setter
@Getter
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, updatable = false, name = "account_id")
    private Long accountId;

    @Column(unique = true, nullable = false, length = 25)
    private String accountNumber;

    @Column(nullable = false, length = 15)
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private LocalDate creationDate;

    @Column(unique = true, nullable = false, length = 25)
    private String cvu;

    @Column(nullable = false)
    private Double balance=0D;

    @Column(nullable = false)
    private Boolean active=false;

    @Column(nullable = false)
    private Long userId;

    public Account(){
        this.balance = 0D;
        this.active = true;
    }

}


