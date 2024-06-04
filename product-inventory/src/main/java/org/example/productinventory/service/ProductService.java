package org.example.productinventory.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.example.productinventory.aspect.TrackUserAction;
import org.example.productinventory.model.Product;
import org.example.productinventory.repository.ProductRepository;
import org.example.productinventory.repository.ShoppingCart;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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
    public Map<String, Integer> getAllProductsInCart(String userName) {
        Map<Product, Integer> originalProductsInCart = shoppingCart.findAll(userName);

        ObjectMapper mapper = new ObjectMapper();

        Map<String, Integer> productsInCartAsString = new HashMap<>();

        for (Map.Entry<Product, Integer> entry : originalProductsInCart.entrySet()) {
            try {
                String productJson = mapper.writeValueAsString(entry.getKey());
                productsInCartAsString.put(productJson, entry.getValue());
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }

        return productsInCartAsString;
    }

    @TrackUserAction
    public void addToShoppingCart(String userName, Long id, int quantity) {
        Product product = productRepository.findById(id).get();
        shoppingCart.save(userName, product, quantity);
    }

    @TrackUserAction
    public double getTotal(String userName) {
        double total = 0;
        for (Map.Entry<Product, Integer> entry : shoppingCart.findAll(userName).entrySet()) {
            total += entry.getValue() * entry.getKey().getPrice();
        }
        return total;
    }

    @TrackUserAction
    public Product getProduct(Long id) { return productRepository.findById(id).get(); }
}
