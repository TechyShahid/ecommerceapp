package com.ecommerce.ecommerceapp.entity;

import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Category {
    @Id
    private int categoryId;
    private String categoryTitle;
    private String categoryDescriptioin;
    @OneToMany
    private List<Product> products=new ArrayList<Product>();
}
