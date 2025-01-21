package com.ecommerce.ecommerceapp.controller;

import com.ecommerce.ecommerceapp.entity.Product;
import com.ecommerce.ecommerceapp.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProdcutController {

    Logger log = LoggerFactory.getLogger(ProdcutController.class);
    @Autowired
    ProductService productService;

    @PostMapping
    public String addProduct(@RequestBody Product product){
     productService.addProduct(product);
        log.info("product added successfully");
        return "product added successfully";

    }
}
