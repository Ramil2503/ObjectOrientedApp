package org.example.webclient.controller;

import lombok.AllArgsConstructor;
import org.example.webclient.service.AdminService;
import org.example.webclient.service.WebService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;
    private final WebService webService;

    @GetMapping
    public String adminHomePage() {
        return "redirect:/admin/products";
    }

    @GetMapping("/products")
    public String productsPage(Model model) {
        model.addAttribute("products", webService.getAllProducts());
        return "admin_products";
    }

    @GetMapping("/orders")
    public String ordersPage(Model model) {
        model.addAttribute("orders", adminService.getAllOrders());
        return "admin_orders";
    }
}
