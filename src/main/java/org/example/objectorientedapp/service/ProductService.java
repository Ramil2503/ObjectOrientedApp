package org.example.objectorientedapp.service;

import lombok.AllArgsConstructor;
import org.example.objectorientedapp.aspect.TrackUserAction;
import org.example.objectorientedapp.model.Product;
import org.example.objectorientedapp.repository.ProductRepository;
import org.example.objectorientedapp.repository.ShoppingCart;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ShoppingCart shoppingCart;

    @TrackUserAction
    public List<Product> getAllProducts() { return productRepository.findAll(); }

    @TrackUserAction
    public Map<Product, Integer> getAllProductsInCart() { return shoppingCart.findAll(); }

    @TrackUserAction
    public void addToShoppingCart(Long id, int quantity) {
        Product product = productRepository.findById(id).get();
        shoppingCart.save(product, quantity);
    }

    @TrackUserAction
    public double getTotal() {
        double total = 0;
        for (Map.Entry<Product, Integer> entry : shoppingCart.findAll().entrySet()) {
            total += entry.getValue() * entry.getKey().getPrice();
        }
        return total;
    }
}
