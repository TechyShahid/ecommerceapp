package com.ecommerce.ecommerceapp.controller;

import com.ecommerce.ecommerceapp.entity.Product;
import com.ecommerce.ecommerceapp.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProdcutController {

    Logger log = LoggerFactory.getLogger(ProdcutController.class);
    @Autowired
    ProductService productService;

    @PostMapping("/addProduct")
    public String addProduct(@RequestBody Product product){
     productService.addProduct(product);
        log.info("product added successfully");
        return "product added successfully";

    }
    @GetMapping("/getAllProducts")
    public List<Product> getAllProcuts(){
        log.info("getting all the products");
        return productService.getAllProducts();
    }
    @GetMapping("/getProductById/{cid}")
    public List<Product> getProductById(@PathVariable List<Integer> cid){
        log.info("get products by cid");
        return productService.getProductById(cid);
    }
}
