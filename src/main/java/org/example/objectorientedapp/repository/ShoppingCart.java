package org.example.objectorientedapp.repository;

import lombok.Data;
import lombok.Getter;
import org.example.objectorientedapp.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ShoppingCart {
    Map<Product, Integer> products;
//    List<Product> products;

    public ShoppingCart() {
        products = new HashMap<>();
    }

    public void save(Product product, int quantity) {
        products.put(product, quantity);
    }

    public Map<Product, Integer> findAll() {
        return products;
    }
}
