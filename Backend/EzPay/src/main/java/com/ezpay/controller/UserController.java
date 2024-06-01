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

    @PatchMapping(value = "/update")
    public ResponseEntity<Map<String,String>> updateUser(@RequestHeader("token") String token, @RequestBody Map<String, Object> updates) throws Exception{
        Integer userId = jwtService.getUserIdFromToken(token);
        return userService.update(userId, updates);
    }

    @PatchMapping(value = "/disable")
    public ResponseEntity<Map<String, String>> deleteUser(@RequestHeader("token") String token) throws Exception {
            Integer userId = jwtService.getUserIdFromToken(token);
            return userService.disable(userId);
    }

    @GetMapping(value = "/findUser")
    public ResponseEntity<Map<String,String>> getUser(@RequestHeader("token") String token) throws Exception {
            Integer userId = jwtService.getUserIdFromToken(token);
            return userService.findUserById(userId);
    }
}
