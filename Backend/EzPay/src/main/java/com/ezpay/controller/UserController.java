package com.ezpay.controller;

import com.ezpay.model.entity.User;
import com.ezpay.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/user")
public class UserController {

    private final  UserService userService;

    @GetMapping(value = "/all")
    public List<User> allUsers(){
        return userService.findAllUsers();
    }

    @PostMapping(value = "/save")
    public String saveUser(@RequestBody User user){
        return userService.save(user);
    }

}
