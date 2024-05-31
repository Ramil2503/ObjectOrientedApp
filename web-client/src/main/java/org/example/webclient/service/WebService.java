package org.example.webclient.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.webclient.model.Product;
import org.example.webclient.model.api.Storage;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WebService {

    private final Storage storageApi;

    public WebService(Storage storageApi) {
        this.storageApi = storageApi;
    }

    public List<Product> getAllProducts() {
        RestTemplate template = new RestTemplate();
        ResponseEntity<List<Product>> response = template.exchange(storageApi.getBasicUri(),
                HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                });
        return response.getBody();
    }


    public Map<Product, Integer> getAllProductsInCart() {
        RestTemplate template = new RestTemplate();
        ResponseEntity<Map<String, Integer>> response = template.exchange(
                storageApi.getBasicUri() + "/products-in-cart",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Map<String, Integer>>() {
                }
        );

        Map<String, Integer> productsInCartAsString = response.getBody();
        ObjectMapper mapper = new ObjectMapper();
        Map<Product, Integer> productsInCart = new HashMap<>();

        for (Map.Entry<String, Integer> entry : productsInCartAsString.entrySet()) {
            try {
                Product product = mapper.readValue(entry.getKey(), Product.class);
                productsInCart.put(product, entry.getValue());
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }

        return productsInCart;
    }

    public void addToShoppingCart(long id, int quantity) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        String url = String.format("%s/addtocart/%d/%d", storageApi.getBasicUri(), id, quantity);

        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                String.class);
    }

    public double getTotal() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Double> response = restTemplate.exchange(
                storageApi.getBasicUri() + "/get-total",
                HttpMethod.GET,
                null,
                Double.class);
        return response.getBody();
    }

    public Product getProduct(long id) {
        RestTemplate template = new RestTemplate();
        ResponseEntity<Product> response = template.exchange(
                storageApi.getBasicUri() + "product/" + id,
                HttpMethod.GET,
                null,
                Product.class);
        return response.getBody();
    }
}
