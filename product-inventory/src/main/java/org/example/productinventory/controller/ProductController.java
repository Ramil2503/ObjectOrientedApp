package org.example.productinventory.controller;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;
import lombok.AllArgsConstructor;
import org.example.productinventory.model.Product;
import org.example.productinventory.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
public class ProductController {
    ProductService productService;

    private final Counter addToCartCounter = Metrics.counter("add-to-cart");

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok().body(productService.getAllProducts());
    }

    @GetMapping("/products-in-cart")
    public ResponseEntity<Map<String, Integer>> getAllProductsInCart() {
        return ResponseEntity.ok().body(productService.getAllProductsInCart());
    }

    @PostMapping("/addtocart/{id}/{quantity}")
    public ResponseEntity<Void> addToCart(@PathVariable Long id, @PathVariable int quantity) {
        productService.addToShoppingCart(id, quantity);
        addToCartCounter.increment();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get-total")
    public ResponseEntity<Double> getTotal() {
        return ResponseEntity.ok().body(productService.getTotal());
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok().body(productService.getProduct(id));
    }
}
