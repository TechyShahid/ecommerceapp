package com.ecommerce.ecommerceapp.controller;

import com.ecommerce.ecommerceapp.entity.User;
import com.ecommerce.ecommerceapp.service.UserService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class UserController {
    Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    UserService userService;

    @PostMapping("/userAuthentication")
    public String authenicateUser(@RequestBody User user){
        log.info("User Controller starts here");
        log.info(user.getName()+"==="+user.getEmail());
        userService.saveUser(user);
        return "user saved successfully";
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
