package org.example.productinventory.controller;

import lombok.AllArgsConstructor;
import org.example.productinventory.model.Product;
import org.example.productinventory.service.OrderService;
import org.example.productinventory.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@AllArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    ProductService productService;
    OrderService orderService;

    @GetMapping("/create-order/{username}")
    public ResponseEntity<Void> createOrder(@PathVariable String username) {
        Map<Product, Integer> productsInCart = productService.getAllProductsInCartInProductFormat(username);

        for (Map.Entry<Product, Integer> entry : productsInCart.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            productService.buyProductUpdateStock(product.getId(), quantity);
            orderService.save(username, product.getId(), quantity);
        }

        return ResponseEntity.ok().build();
    }

    @GetMapping("/get-all-orders")
    public ResponseEntity<Map<String, String>> getAllOrders() {
        return ResponseEntity.ok().body(orderService.getAllOrders());
    }
}
