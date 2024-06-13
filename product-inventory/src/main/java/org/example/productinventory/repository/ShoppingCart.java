package org.example.productinventory.repository;

import org.example.productinventory.model.Product;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ShoppingCart {

    private final Map<String, Map<Product, Integer>> userCarts = new HashMap<>();

    public void save(String userName, Product product, int quantity) {
        userCarts.computeIfAbsent(userName, k -> new HashMap<>()).put(product, quantity);
    }

    public Map<Product, Integer> findAll(String userName) {
        return userCarts.getOrDefault(userName, new HashMap<>());
    }

    public void clear(String userName) {
        userCarts.remove(userName);
    }

    public void deleteProduct(String userName, Long productId) {
        Map<Product, Integer> userCart = userCarts.get(userName);
        if (userCart != null) {
            userCart.entrySet().removeIf(entry -> entry.getKey().getId().equals(productId));
        }
    }
}
