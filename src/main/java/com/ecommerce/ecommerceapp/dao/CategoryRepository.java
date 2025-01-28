package com.ecommerce.ecommerceapp.dao;


import com.ecommerce.ecommerceapp.entity.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoryRepository extends MongoRepository<Category,Integer> {
}
