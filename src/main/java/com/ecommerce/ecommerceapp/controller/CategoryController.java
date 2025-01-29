package com.ecommerce.ecommerceapp.controller;

import com.ecommerce.ecommerceapp.entity.Category;
import com.ecommerce.ecommerceapp.payload.response.MessageResponse;
import com.ecommerce.ecommerceapp.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {
    Logger log = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    CategoryService categoryService;

    @PostMapping("/addCategory")
    public ResponseEntity<?> addCategory(@RequestBody Category category){
        log.info("add category controller");
        try {
        categoryService.addCategory(category);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
        }catch (Exception e){
            log.error("Exception while saving the category:  "+e.getMessage());
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Exception while saving the category:  "+e.getMessage()));
        }
    }
    @GetMapping("/getAllCategory")
    public ResponseEntity<?> getAllCategory(){
       try {
           List<Category> categoryList = categoryService.getAllCategory();
           if (categoryList != null) {
               return ResponseEntity.ok().body(categoryList);
           } else {
               return ResponseEntity
                       .badRequest()
                       .body(new MessageResponse("Error: No category is not present"));
           }
       }catch (Exception e){
           log.error("Exception while getting the category:  "+e.getMessage());
           return ResponseEntity
                   .badRequest()
                   .body(new MessageResponse("Exception while getting the category:  "+e.getMessage()));
       }
    }
    @GetMapping("/getCategoryById/{cid}")
    public ResponseEntity<?> getCategoryById(@PathVariable Integer cid) {
        log.info("getting category by Id");
        try {
            Category category = categoryService.getCategoryById(cid).isPresent() ? categoryService.getCategoryById(cid).get() : null;
            if (category != null) {
                return ResponseEntity.ok().body(category);
            } else {
                return ResponseEntity
                        .badRequest()
                        .body(new MessageResponse("Error: Category is not present"));
            }
        } catch (Exception e) {
            log.error("Exception while getting the category:  " + e.getMessage());
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Exception while getting the category:  " + e.getMessage()));

        }
    }
}
