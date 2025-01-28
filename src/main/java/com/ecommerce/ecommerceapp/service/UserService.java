package com.ecommerce.ecommerceapp.service;

import com.ecommerce.ecommerceapp.dao.UserDAO;
import com.ecommerce.ecommerceapp.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    Logger log = LoggerFactory.getLogger(UserService.class);
    @Autowired
    UserDAO userDAO;
    public User saveUser(User user){
        userDAO.save(user);
        return user;
    }
    public List<User> getAllUsers(){
        return userDAO.findAll();
    }
    public void deleteAllUsers(){
        userDAO.deleteAll();
    }

}
