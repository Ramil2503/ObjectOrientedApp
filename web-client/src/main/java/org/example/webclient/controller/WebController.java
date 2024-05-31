package org.example.webclient.controller;

import lombok.AllArgsConstructor;
import org.example.webclient.service.WebService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class WebController {
    private final WebService webService;

    @GetMapping
    public String getAllProducts(Model model) {
        model.addAttribute("products", webService.getAllProducts());
        model.addAttribute("productsincart", webService.getAllProductsInCart());
        return "home";
    }

    @PostMapping("/addtocart/{id}")
    public String addToCart(@PathVariable Long id, @RequestParam int quantity, Model model) {
        webService.addToShoppingCart(id, quantity);
        model.addAttribute("products", webService.getAllProducts());
        return "redirect:/";
    }

    @PostMapping("/addtocart_product-page/{id}")
    public String addToCartFromProductPage(@PathVariable Long id, @RequestParam int quantity, Model model) {
        webService.addToShoppingCart(id, quantity);
        model.addAttribute("products", webService.getAllProducts());
        return "redirect:/product/" + id;
    }

    @GetMapping("/shoppingcart")
    public String viewShoppingCart(Model model) {
        model.addAttribute("products", webService.getAllProductsInCart());
        model.addAttribute("productsincart", webService.getAllProductsInCart());
        model.addAttribute("total", webService.getTotal());
        return "shopping_cart";
    }

    @GetMapping("product/{id}")
    public String viewProduct(@PathVariable Long id, Model model) {
        model.addAttribute("product", webService.getProduct(id));
        model.addAttribute("productsincart", webService.getAllProductsInCart());
        return "product_page";
    }
}
