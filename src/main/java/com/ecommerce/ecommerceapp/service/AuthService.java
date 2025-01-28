package com.ecommerce.ecommerceapp.service;

import com.ecommerce.ecommerceapp.dao.UserRepository;
import com.ecommerce.ecommerceapp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    UserRepository userRepository;

    public Optional<User> findByUsername(String username){
        return userRepository.findByUsername(username);
    }
    public Boolean existsByUsername(String username){
        return userRepository.existsByUsername(username);
    }
    public Boolean existsByEmail(String email){
        return userRepository.existsByEmail(email);
    }
    public void saveUser(User user){
        userRepository.save(user);
    }
}
