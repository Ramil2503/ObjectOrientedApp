package org.example.webclient.controller;

import lombok.AllArgsConstructor;
import org.example.webclient.service.WebService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final WebService webService;

    @GetMapping
    public String adminHomePage() {
        return "admin";
    }

    @GetMapping("/products")
    public String productsPage() {
        return "admin_products";
    }

    @GetMapping("/orders")
    public String ordersPage() {
        return "admin_orders";
    }
}
