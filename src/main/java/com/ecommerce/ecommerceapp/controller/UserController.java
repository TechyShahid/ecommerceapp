package com.ecommerce.ecommerceapp.controller;

import com.ecommerce.ecommerceapp.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import com.ecommerce.ecommerceapp.service.UserService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class UserController {
    Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    UserService userService;
    @Autowired
    UserDetailsService userDetailsService;

    @PostMapping("/userRegistration")
    public String userResistration(@RequestBody User user){
        log.info("User Controller starts here");
        //log.info(user.getName()+"==="+user.getEmail());
        userService.saveUser(user);
        return "user saved successfully";
    }
    @PostMapping("/userAuthentication")
    public boolean authenicateUser(@RequestBody User user){
        log.info("User Controller starts here");
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());

        return userDetails!=null;
    }
    @GetMapping("/getAllUser")
    public List<User> getAllUser() {
        return userService.getAllUsers();
    }
    @DeleteMapping("deleteAllUser")
    public String deleteAllUser(){
        userService.deleteAllUsers();
        return "deleted all the users";
    }
}
