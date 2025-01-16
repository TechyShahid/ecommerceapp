package com.ecommerce.ecommerceapp.dao;

import com.ecommerce.ecommerceapp.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends MongoRepository<User, Integer> {
}
