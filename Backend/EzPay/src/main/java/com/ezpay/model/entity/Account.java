package com.ezpay.model.entity;

import com.ezpay.model.enums.AccountType;
import com.ezpay.model.enums.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @Column(unique = true, nullable = false, updatable = false, name = "account_id", length = 25)
    private String accountId;

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
    private Double dailyLimit=0D;

    @Column(nullable = false)
    private Boolean isActive=false;

    @Column(name = "user_id", unique = true, nullable = false)
    private Integer userId;


    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", updatable = false, insertable = false)
    private User user;


    public Account(){
        this.balance = 0D;
        this.isActive = true;
        this.dailyLimit = 0D;
    }

}


