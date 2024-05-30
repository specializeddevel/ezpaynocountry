package com.ezpay.controller;

import com.ezpay.model.entity.User;
import com.ezpay.service.JwtService;
import com.ezpay.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/user")
public class UserController {

    private final  UserService userService;
    private final JwtService jwtService;

    @GetMapping(value = "/all")
    public List<User> allUsers(){
        return userService.findAllUsers();
    }

    @PostMapping(value = "/save")
    public String saveUser(@RequestBody User user){
        return userService.save(user);
    }

    @PatchMapping(value = "/update")
    public ResponseEntity<String> updateUser(@RequestHeader("token") String token, @RequestBody Map<String, Object> updates) throws Exception{
        Integer userId = jwtService.getUserIdFromToken(token);
        return userService.update(userId, updates);
    }

    @PatchMapping(value = "/delete")
    public String deleteUser(@RequestHeader("token") String token) throws Exception{
        Integer userId = jwtService.getUserIdFromToken(token);
        return userService.delete(userId);
    }

    @GetMapping(value = "/findUser")
    public ResponseEntity<User> getUser(@RequestHeader("token") String token) throws Exception{
        Integer userId = jwtService.getUserIdFromToken(token);
        return ResponseEntity.ok(userService.findUserById(userId));
    }





}
