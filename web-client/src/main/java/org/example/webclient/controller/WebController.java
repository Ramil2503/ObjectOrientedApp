package org.example.webclient.controller;

import lombok.AllArgsConstructor;
import org.example.webclient.service.WebService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String addToCart(@PathVariable Long id, @RequestParam int quantity, @RequestHeader(value = "Referer", required = false) String referer, Model model) {
        webService.addToShoppingCart(id, quantity);
        model.addAttribute("products", webService.getAllProducts());
        if (referer != null) {
            return "redirect:" + referer;
        } else {
            return "redirect:/"; // Fallback if the Referer header is not present
        }
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

    @GetMapping("/admin")
    public String admin(Model model) {
        return "admin";
    }

    @GetMapping("/checkout")
    public String checkout(Model model) {
        model.addAttribute("products", webService.getAllProductsInCart());
        model.addAttribute("productsincart", webService.getAllProductsInCart());
        model.addAttribute("total", webService.getTotal());
        return "checkout";
    }

    @GetMapping("/confirm")
    public String confirm(@RequestParam("cardNumber") String cardNumber,
                          @RequestParam("email") String email,
                          @RequestParam("name") String name,
                          @RequestParam("surname") String surname,
                          @RequestParam("ccv") String ccv,
                          Model model) {
        webService.clearShoppingCart();
        return "confirm";
    }

    @GetMapping("/delete-product-shopping-cart/{id}")
    public String deleteProductShoppingCart(@PathVariable Long id, @RequestHeader(value = "Referer", required = false) String referer) {
        webService.deleteProductShoppingCart(id);
        if (referer != null) {
            return "redirect:" + referer;
        } else {
            return "redirect:/"; // Fallback if the Referer header is not present
        }
    }
}
