package com.ezpay.utils.dto.User;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;

import java.util.Date;

public record UserUpdateDto (

    String firstName,


    String lastName,

    @Pattern(regexp = "^\\+?[0-9]{7,25}$", message = "Phone number is invalid")
    String phoneNumber,


    @Pattern(regexp = "^[0-9]{8,12}$", message = "DNI should be between 8 and 12 digits")
    String dni,

    @Past(message = "Birth date must be in the past")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    Date birthDate,


    @Pattern(regexp = "MASCULINO|FEMENINO|NO_BINARIO|PREFIERO_NO_DECIR", message = "Gender should be MASCULINO, FEMENINO or PREFIERO_NO_DECIR")
    String gender){}
