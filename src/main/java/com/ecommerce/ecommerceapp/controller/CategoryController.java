package com.ecommerce.ecommerceapp.controller;

import com.ecommerce.ecommerceapp.entity.Category;
import com.ecommerce.ecommerceapp.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {
    Logger log = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    CategoryService categoryService;

    @PostMapping("/addCategory")
    public String addCategory(@RequestBody Category category){
        log.info("add category controller");
        categoryService.addCategory(category);
        return "added category successfully";
    }
    @GetMapping("/getAllCategory")
    public List<Category> getAllCategory(){
        log.info("getting all the category");
        return categoryService.getAllCategory();
    }
    @GetMapping("/getCategoryById/{cid}")
    public Category getCategoryById(@PathVariable Integer cid){
        log.info("getting category by Id");
        return categoryService.getCategoryById(cid).isPresent()?categoryService.getCategoryById(cid).get():new Category();

    }
}
