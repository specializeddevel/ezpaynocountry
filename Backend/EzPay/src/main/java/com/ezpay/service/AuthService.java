package com.ezpay.service;

import com.ezpay.model.entity.User;
import com.ezpay.model.enums.Gender;
import com.ezpay.security.request.LoginRequest;
import com.ezpay.security.request.RegisterRequest;
import com.ezpay.security.response.AuthResponse;
import com.ezpay.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.ezpay.model.enums.Role;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        UserDetails user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new UsernameNotFoundException("Email not found"));
        String token = jwtService.getToken(user);
        return AuthResponse.builder()
                .token(token)
                .build();
    }


    public AuthResponse register(RegisterRequest registerRequest) {
        // Obtener la fecha actual para el registro del usuario
        Date currentDate = new Date();
        Timestamp currentTimestamp = new Timestamp(currentDate.getTime());

        User user = User.builder()
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .phoneNumber(registerRequest.getPhoneNumber())
                .dni(registerRequest.getDni())
                .birthDate(registerRequest.getBirthDate())
                .gender(Gender.valueOf(registerRequest.getGender()))
                .role(Role.USER) // Por defecto los nuevos usuarios tendrán el rol USER.
                .createdAt(currentTimestamp) // Establecer la fecha de creación del usuario
                .build();

        userRepository.save(user);
        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();
    }
}