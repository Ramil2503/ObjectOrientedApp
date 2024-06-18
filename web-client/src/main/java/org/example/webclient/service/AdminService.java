package org.example.webclient.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.webclient.model.Product;
import org.example.webclient.model.api.Storage;
import org.hibernate.query.Order;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class AdminService {

    private final Storage storageApi;

    public AdminService(Storage storageApi) {
        this.storageApi = storageApi;
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
}
