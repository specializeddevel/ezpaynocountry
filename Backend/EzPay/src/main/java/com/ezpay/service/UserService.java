package com.ezpay.service;

import com.ezpay.model.entity.User;
import com.ezpay.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> findAllUsers(){return userRepository.findAll();}
    public Optional<User> findUserById(Integer userId){return userRepository.findById(userId);}
    public String save(User user){userRepository.save(user); return "User saved";}
    public Optional<User> findUserByEmail(String email){return userRepository.findByEmail(email);}
}
