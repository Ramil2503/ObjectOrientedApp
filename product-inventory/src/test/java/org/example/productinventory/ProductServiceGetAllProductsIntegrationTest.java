package org.example.productinventory;

import org.example.productinventory.model.Product;
import org.example.productinventory.repository.ProductRepository;
import org.example.productinventory.repository.ShoppingCart;
import org.example.productinventory.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.jdbc.Sql;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@Sql(scripts = "/data.sql")
public class ProductServiceGetAllProductsIntegrationTest {
    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    @MockBean
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
        verify(productRepository).findAll();
    }
}
