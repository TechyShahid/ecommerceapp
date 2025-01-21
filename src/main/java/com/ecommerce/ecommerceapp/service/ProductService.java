package com.ecommerce.ecommerceapp.service;

import com.ecommerce.ecommerceapp.dao.ProductDAO;
import com.ecommerce.ecommerceapp.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductDAO productDAO;

    public void addProduct(Product product){
        productDAO.save(product);
    }
    public void addProducts(List<Product> productList){
        productDAO.saveAll(productList);
    }
    public List<Product> getAllProducts(){
        return productDAO.findAll();
    }
    public List<Product> getProductById(List<Integer> cid){
        return productDAO.findAllById(cid);

    }
}
