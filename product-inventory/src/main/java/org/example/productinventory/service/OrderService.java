package org.example.productinventory.service;

import lombok.AllArgsConstructor;
import org.example.productinventory.model.Orders;
import org.example.productinventory.model.OrderItem;
import org.example.productinventory.model.Product;
import org.example.productinventory.repository.OrderItemRepository;
import org.example.productinventory.repository.OrderRepository;
import org.example.productinventory.repository.ProductRepository;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class OrderService {

    private OrderRepository orderRepository;

    private ProductRepository productRepository;

    private OrderItemRepository orderItemRepository;

    @Transactional
    public void save(String userName, Long productId, int quantity) {
        Orders orders = orderRepository.findByUserName(userName);
        if (orders == null) {
            orders = new Orders();
            orders.setUserName(userName);
            orderRepository.save(orders);
        }

        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
        OrderItem item = new OrderItem();
        item.setOrders(orders);
        item.setProduct(product);
        item.setQuantity(quantity);

        orders.getItems().add(item);
        orderItemRepository.save(item);
    }

    @Transactional
    public Map<String, Map<Product, Integer>> getAllOrders() {
        List<Orders> allOrders = orderRepository.findAll();
        Map<String, Map<Product, Integer>> result = new HashMap<>();

        for (Orders orders : allOrders) {
            Map<Product, Integer> productQuantityMap = new HashMap<>();
            for (OrderItem item : orders.getItems()) {
                Product product = item.getProduct();
                int quantity = item.getQuantity();
                productQuantityMap.put(product, quantity);
            }
            result.put(orders.getUserName(), productQuantityMap);
        }

        return result;
    }

    @Transactional
    public List<OrderItem> findAllOrdersOfUser(String userName) {
        Orders orders = orderRepository.findByUserName(userName);
        if (orders != null) {
            // Initialize the collection
            orders.getItems().size(); // Access the size to force initialization
            return orders.getItems();
        }
        return List.of();
    }

    @Transactional
    public void clear(String userName) {
        Orders orders = orderRepository.findByUserName(userName);
        if (orders != null) {
            orderItemRepository.deleteAll(orders.getItems());
            orderRepository.delete(orders);
        }
    }

    @Transactional
    public void deleteProduct(String userName, Long productId) {
        Orders orders = orderRepository.findByUserName(userName);
        if (orders != null) {
            orders.getItems().removeIf(item -> item.getProduct().getId().equals(productId));
            orderRepository.save(orders);
        }
    }
}
