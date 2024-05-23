package com.ezpay.security.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    String firstName;
    String lastName;
    String email;
    String password;
    String phoneNumber;
    String dni;
    Date birthDate;
    String gender;
}