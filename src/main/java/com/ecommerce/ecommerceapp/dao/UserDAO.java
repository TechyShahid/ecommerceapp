package com.ecommerce.ecommerceapp.dao;

import com.ecommerce.ecommerceapp.entity.User;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDAO extends MongoRepository<User, Integer> {

}
