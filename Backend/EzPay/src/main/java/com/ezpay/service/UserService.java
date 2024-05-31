package com.ezpay.service;

import com.ezpay.exceptions.UserNotFoundException;
import com.ezpay.model.entity.User;
import com.ezpay.model.enums.Gender;
import com.ezpay.repository.UserRepository;
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
    private final PasswordEncoder passwordEncoder;

    public List<User> findAllUsers(){return userRepository.findAll();}

    public ResponseEntity<Map<String, String>> findUserById(Integer userId) throws Exception {
        try {
            Optional<User> userOptional = userRepository.findById(userId);
            User user = userOptional.orElseThrow(() -> new UserNotFoundException(userId));

            Map<String, String> response = new HashMap<>();
            response.put("success", "User found");
            return ResponseEntity.ok(response);

        } catch (UserNotFoundException e) {
            Map<String, String> response = new HashMap<>();
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "An unexpected error occurred");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }


    }

    public ResponseEntity<Map<String, String>> disable(Integer userId) throws Exception {
        try {
            Optional<User> userOptional = userRepository.findById(userId);
            User user = userOptional.orElseThrow(() -> new UserNotFoundException(userId));
            user.setUserEnabled(false);
            userRepository.save(user);
            Map<String, String> response = new HashMap<>();
            response.put("success", "User disabled successfully");
            return ResponseEntity.ok(response);
        } catch (UserNotFoundException e) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "User not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "An unexpected error occurred");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    public ResponseEntity<Map<String, String>> update(Integer userId, @RequestBody Map<String, Object> updates) throws Exception {
        Optional<User> userOptional = userRepository.findById(userId);
        User user = userOptional.orElseThrow(() -> new UserNotFoundException(userId));

        if (updates.containsKey("firstName")) {
            user.setFirstName((String) updates.get("firstName"));
        }
        if (updates.containsKey("lastName")) {
            user.setLastName((String) updates.get("lastName"));
        }
        if (updates.containsKey("email")) {
            user.setEmail((String) updates.get("email"));
        }
        if (updates.containsKey("password")) {
            user.setPassword(passwordEncoder.encode((String) updates.get("password")));
        }
        if (updates.containsKey("phoneNumber")) {
            user.setPhoneNumber((String) updates.get("phoneNumber"));
        }
        if (updates.containsKey("dni")) {
            user.setDni((String) updates.get("dni"));
        }
        if (updates.containsKey("birthDate")) {
            try {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                user.setBirthDate(dateFormat.parse((String) updates.get("birthDate")));
            } catch (ParseException e) {
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("error", "Invalid date format");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
            }
        }
        if (updates.containsKey("gender")) {
            try {
                user.setGender(Gender.valueOf((String) updates.get("gender")));
            } catch (IllegalArgumentException e) {
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("error", "Invalid gender value");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
            }
        }

        userRepository.save(user);

        Map<String, String> successResponse = new HashMap<>();
        successResponse.put("success", "User updated successfully");
        return ResponseEntity.ok(successResponse);
    }
}
