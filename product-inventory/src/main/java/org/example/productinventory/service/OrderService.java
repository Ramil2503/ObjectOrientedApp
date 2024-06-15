package org.example.productinventory.service;

import lombok.AllArgsConstructor;
import org.example.productinventory.model.Orders;
import org.example.productinventory.model.OrderItem;
import org.example.productinventory.model.Product;
import org.example.productinventory.repository.OrderItemRepository;
import org.example.productinventory.repository.OrderRepository;
import org.example.productinventory.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.List;

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
    public List<OrderItem> findAll(String userName) {
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
