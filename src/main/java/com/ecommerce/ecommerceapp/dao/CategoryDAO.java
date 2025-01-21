package com.ecommerce.ecommerceapp.dao;


import com.ecommerce.ecommerceapp.entity.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoryDAO extends MongoRepository<Category,Integer> {
}
