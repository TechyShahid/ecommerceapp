package com.ecommerce.ecommerceapp.controller;

import com.ecommerce.ecommerceapp.aop.LogExecutionTime;
import com.ecommerce.ecommerceapp.entity.Product;
import com.ecommerce.ecommerceapp.payload.response.MessageResponse;
import com.ecommerce.ecommerceapp.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProdcutController {

    Logger log = LoggerFactory.getLogger(ProdcutController.class);
    @Autowired
    ProductService productService;

    @LogExecutionTime
    @PostMapping("/addProduct")
    public ResponseEntity<?> addProduct(@RequestBody Product product){
        try {
            productService.addProduct(product);
            log.info("product added successfully");
            return ResponseEntity.ok().body(new MessageResponse("product added successfully"));
        }catch (Exception e){
            log.error("Exception while saving the product:  "+e.getMessage());
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Exception while saving the product:  "+e.getMessage()));
        }

    }
    @LogExecutionTime
    @GetMapping("/getAllProducts")
    public ResponseEntity<?> getAllProcuts(){
        try {
            log.info("getting all the products");
            return ResponseEntity.ok().body(productService.getAllProducts());
        }catch (Exception e){
        log.error("Exception while getting the product:  "+e.getMessage());
        return ResponseEntity
                .badRequest()
                .body(new MessageResponse("Exception while getting the product:  "+e.getMessage()));
    }
    }
    @LogExecutionTime
    @GetMapping("/getProductById/{cid}")
    public ResponseEntity<?> getProductById(@PathVariable List<Integer> cid){
        try{
        log.info("get products by cid");
        return ResponseEntity.ok().body(productService.getProductById(cid));
    }catch (Exception e){
        log.error("Exception while getting the product:  "+e.getMessage());
        return ResponseEntity
                .badRequest()
                .body(new MessageResponse("Exception while getting the product:  "+e.getMessage()));
    }
    }
}
