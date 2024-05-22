package com.ezpay.model.entity;

import com.ezpay.model.enums.Gender;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer user_id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "dni")
    private String dni;

    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "gender", columnDefinition = "VARCHAR(17) default 'PREFIERO_NO_DECIR'")
    @Enumerated(EnumType.STRING)
    private Gender gender;

}
