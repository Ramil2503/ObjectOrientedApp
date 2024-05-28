package org.example.productinventory;

import org.example.productinventory.model.Product;
import org.example.productinventory.repository.ProductRepository;
import org.example.productinventory.repository.ShoppingCart;
import org.example.productinventory.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceGetAllProductsUnitTest {
    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ShoppingCart shoppingCart;

    @Test
    public void getAllProductsTest() {
        // Precondition
        List<Product> products = Arrays.asList(new Product(), new Product());
        when(productRepository.findAll()).thenReturn(products);

        // Action
        List<Product> result = productService.getAllProducts();

        // Verification
        assertEquals(2, result.size());
    }
}
