package com.ezpay.service;

import com.ezpay.exceptions.InvalidDateFormatException;
import com.ezpay.exceptions.InvalidGenderValueException;
import com.ezpay.exceptions.UserNotFoundException;
import com.ezpay.model.entity.User;
import com.ezpay.model.enums.Gender;
import com.ezpay.repository.UserRepository;
import com.ezpay.utils.dto.User.UserUpdateDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> findUserById(Integer userId)  {
        return Optional.ofNullable(userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId)));

    }

    public void disable(Integer userId) throws Exception {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        user.setUserEnabled(false);

        userRepository.save(user);
    }

    public void update(Integer userId, UserUpdateDto updates) throws Exception {
        Optional<User> userOptional = userRepository.findById(userId);
        User user = userOptional.orElseThrow(() -> new UserNotFoundException(userId));

        if (updates.firstName() != null) {
            user.setFirstName(updates.firstName());
        }
        if (updates.lastName() != null) {
            user.setLastName(updates.lastName());
        }
        if (updates.phoneNumber() != null) {
            user.setPhoneNumber(updates.phoneNumber());
        }
        if (updates.dni() != null) {
            user.setDni(updates.dni());
        }
        if (updates.birthDate() != null) {
            user.setBirthDate(updates.birthDate());
        }
        if (updates.gender() != null) {
            try {
                user.setGender(Gender.valueOf(updates.gender().toUpperCase()));
            } catch (IllegalArgumentException e) {
                throw new InvalidGenderValueException("Invalid gender value", e);
            }
        }
        userRepository.save(user);
    }
}
