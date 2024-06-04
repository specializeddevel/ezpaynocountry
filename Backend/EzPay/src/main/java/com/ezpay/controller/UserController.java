package com.ezpay.controller;

import com.ezpay.exceptions.UserNotFoundException;
import com.ezpay.model.entity.User;
import com.ezpay.service.JwtService;
import com.ezpay.service.UserService;
import com.ezpay.utils.dto.User.UserUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.service.GenericResponseService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/user")
public class UserController {

    private final UserService userService;
    private final JwtService jwtService;
    private final GenericResponseService responseBuilder;

    @GetMapping(value = "/all")
    public ResponseEntity<List<User>> allUsers() {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("my-custom-header", "my-custom-value");
        return new ResponseEntity<>(userService.findAllUsers(), responseHeaders, HttpStatus.OK);
    }

    @PatchMapping(value = "/update")
    public ResponseEntity<?> updateUser(
            @RequestHeader("token") String token,
            @Validated @RequestBody UserUpdateDto updates) throws Exception {

        Integer userId = jwtService.getUserIdFromToken(token);
        userService.update(userId, updates);
        return ResponseEntity.ok("User updated Successfully");
    }


    @PatchMapping(value = "/disable")
    public ResponseEntity<Map<String, String>> disableUser(@RequestHeader("token") String token) {
        Integer userId = jwtService.getUserIdFromToken(token);

        try {
            userService.disable(userId);
            Map<String, String> response = new HashMap<>();
            response.put("success", "User disabled successfully");
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

    @GetMapping(value = "/findUser")
    public ResponseEntity<Map<String, String>> getUser(@RequestHeader("token") String token) {
        Integer userId = jwtService.getUserIdFromToken(token);

        try {
            userService.findUserById(userId);
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
}