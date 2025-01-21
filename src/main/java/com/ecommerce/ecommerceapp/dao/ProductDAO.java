package com.ecommerce.ecommerceapp.dao;

import com.ecommerce.ecommerceapp.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDAO extends MongoRepository<Product,Integer> {
}
