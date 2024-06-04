package com.ezpay.security.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
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

    @NotBlank(message = "First name cannot be blank")
    private String firstName;

    @NotBlank(message = "Last name cannot be blank")
    private String lastName;

    @Email(message = "Email should be valid, example@email.com")
    @NotBlank(message = "Email cannot be blank")
    String email;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 6, message = "Password should have at least 6 characters")
    String password;

    @NotBlank(message = "Phone number cannot be blank")
    @Pattern(regexp = "^\\+?[0-9]{7,25}$", message = "Phone number is invalid")
    String phoneNumber;

    @NotBlank(message = "DNI cannot be blank")
    @Pattern(regexp = "^[0-9]{8,12}$", message = "DNI should be between 8 and 12 digits")
    String dni;

    @NotNull(message = "Birth date cannot be null")
    @Past(message = "Birth date must be in the past")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date birthDate;

    @NotBlank(message = "Gender cannot be blank")
    @Pattern(regexp = "MASCULINO|FEMENINO|NO_BINARIO|PREFIERO_NO_DECIR", message = "Gender should be MASCULINO, FEMENINO or PREFIERO_NO_DECIR")
    private String gender;
}