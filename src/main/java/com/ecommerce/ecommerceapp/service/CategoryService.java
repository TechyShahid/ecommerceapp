package com.ecommerce.ecommerceapp.service;

import com.ecommerce.ecommerceapp.dao.CategoryDAO;
import com.ecommerce.ecommerceapp.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryDAO categoryDAO;

    public void addCategory(Category category){
        categoryDAO.save(category);
    }
    public void addCategories(List<Category> categoryList){
        categoryDAO.saveAll(categoryList);
    }
    public List<Category> getAllCategory(){
        return categoryDAO.findAll();
    }
    public Optional<Category> getCategoryById(int cid){
        return categoryDAO.findById(cid);
    }
}
