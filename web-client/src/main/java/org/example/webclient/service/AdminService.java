package org.example.webclient.service;

import org.example.webclient.model.Product;
import org.example.webclient.model.api.Storage;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

@Service
public class AdminService {
    private final ImageService imageService;
    private final Storage storageApi;

    public AdminService(Storage storageApi, ImageService imageService) {
        this.storageApi = storageApi; this.imageService = imageService;
    }

    // Map<String, Map<Product, Integer>>
    // instead of this complex Map; we can use Map<String, String>
    public Map<String, String> getAllOrders() {
        RestTemplate template = new RestTemplate();
        String url = String.format("%s/orders/get-all-orders", storageApi.getBasicUri());
        ResponseEntity<Map<String, String>> response = template.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );

        return response.getBody();
    }

    public void addProduct(String name, String description, Double price, Integer inStock, MultipartFile image) {
        RestTemplate template = new RestTemplate();
        String url = String.format("%s/add-product", storageApi.getBasicUri());
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setInStock(inStock);

        ResponseEntity<Long> response = template.postForEntity(url, product, Long.class);
        Long productId = response.getBody();

        if (productId != null) {
            saveImage(image, productId);
        }
    }

    public void saveImage(MultipartFile image, Long productId) {
//        try {
//            byte[] bytes = image.getBytes();
//            Path path = Paths.get("web-client/src/main/resources/static/assets/" + productId + ".jpg");
//            Files.write(path, bytes);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        imageService.saveImage(image, productId);
    }

    public void deleteProduct(Long id) {
        // TODO: implement delete product from product inventory
        imageService.deleteImage(id);
    }
}
