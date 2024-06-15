package org.example.productinventory.controller;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;
import lombok.AllArgsConstructor;
import org.example.productinventory.model.Product;
import org.example.productinventory.service.OrderService;
import org.example.productinventory.service.FileGateway;
import org.example.productinventory.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    OrderService orderService;
    private final FileGateway fileGateway;
    private final Counter addToCartCounter = Metrics.counter("add-to-cart");

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok().body(productService.getAllProducts());
    }

    @GetMapping("/{username}/products-in-cart")
    public ResponseEntity<Map<String, Integer>> getAllProductsInCart(@PathVariable String username) {
        return ResponseEntity.ok().body(productService.getAllProductsInCart(username));
    }

    @PostMapping("/addtocart/{username}/{id}/{quantity}")
    public ResponseEntity<Void> addToCart(@PathVariable String username, @PathVariable Long id, @PathVariable int quantity) {
        productService.addToShoppingCart(username, id, quantity);
        addToCartCounter.increment();
        fileGateway.writeToFile("products.txt", productService.getProduct(id).getName() + "; QUANTITY ADDED TO CART: " + quantity);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{username}/get-total")
    public ResponseEntity<Double> getTotal(@PathVariable String username) {
        return ResponseEntity.ok().body(productService.getTotal(username));
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok().body(productService.getProduct(id));
    }

    @GetMapping("/{username}/clear-shopping-cart")
    public ResponseEntity<Void> clearShoppingCart(@PathVariable String username) {
        productService.clearShoppingCart(username);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{username}/delete-product-shopping-cart/{id}")
    public ResponseEntity<Void> deleteProductShoppingCart(@PathVariable String username, @PathVariable Long id) {
        productService.deleteProductShoppingCart(username, id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/test")
    public ResponseEntity<Void> test() {
        System.out.println(orderService.findAll("user"));
        return ResponseEntity.ok().build();
    }
}
