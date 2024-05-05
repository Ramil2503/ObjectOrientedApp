package org.example.objectorientedapp.controller;

import lombok.AllArgsConstructor;
import org.example.objectorientedapp.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class ProductController {

    ProductService productService;

    @GetMapping
    public String getAllProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "home";
    }

    @PostMapping("addtocart/{id}")
    public String addToCart(@PathVariable Long id, @RequestParam int quantity,  Model model) {
        productService.addToShoppingCart(id, quantity);
        System.out.println(productService.getAllProductsInCart());
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("productsincart", productService.getAllProductsInCart());
        return "home";
    }
}
