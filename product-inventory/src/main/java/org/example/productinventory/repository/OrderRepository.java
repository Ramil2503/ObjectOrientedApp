package org.example.productinventory.repository;

import org.example.productinventory.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Orders, Long> {
    @Query("SELECT o FROM Orders o WHERE o.userName = ?1")
    Orders findByUserName(String userName);
}
