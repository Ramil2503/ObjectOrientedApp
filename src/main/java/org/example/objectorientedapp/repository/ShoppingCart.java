package org.example.objectorientedapp.repository;

import org.example.objectorientedapp.model.Product;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ShoppingCart {
    Map<Product, Integer> products;

    public ShoppingCart() {
        products = new HashMap<>();
    }

    public void save(Product product, int quantity) {
        products.put(product, quantity);
    }

    public Map<Product, Integer> findAll() {
        Map<Product, Integer> result = new HashMap<>();
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            if (entry.getValue() > 0) {
                result.put(entry.getKey(), entry.getValue());
            }
        }
        return result;
    }
}
