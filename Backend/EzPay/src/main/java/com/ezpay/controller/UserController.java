package com.ezpay.controller;

import com.ezpay.exceptions.UserNotFoundException;
import com.ezpay.model.entity.User;
import com.ezpay.service.JwtService;
import com.ezpay.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.service.GenericResponseService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/user")
public class UserController {

    private final  UserService userService;
    private final JwtService jwtService;
    private final GenericResponseService responseBuilder;

    @GetMapping(value = "/all")
    public ResponseEntity<List<User>> allUsers(){
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("my-custom-header", "my-custom-value");
        return new ResponseEntity<>(userService.findAllUsers(), responseHeaders, HttpStatus.OK);
    }

    @PostMapping(value = "/save")
    public ResponseEntity<String> saveUser(@RequestBody User user){
        return userService.save(user);
    }

    @PatchMapping(value = "/update")
    public ResponseEntity<Map<String,String>> updateUser(@RequestHeader("token") String token, @RequestBody Map<String, Object> updates) throws Exception{
        Integer userId = jwtService.getUserIdFromToken(token);
        return userService.update(userId, updates);
    }

    @PatchMapping(value = "/delete")
    public ResponseEntity<Map<String, String>> deleteUser(@RequestHeader("token") String token) {
        try {
            Integer userId = jwtService.getUserIdFromToken(token);
            userService.delete(userId);
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

    @GetMapping(value = "/findUser")
    public ResponseEntity<?> getUser(@RequestHeader("token") String token) {
        try {
            Integer userId = jwtService.getUserIdFromToken(token);
            User user = userService.findUserById(userId);
            return ResponseEntity.ok(user);
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
}
