package org.example.webclient.repository;

import org.example.webclient.model.Userr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<Userr, Long> {
    @Query("SELECT u FROM Userr u WHERE u.username = ?1")
    Userr findByUsername(String username);
}
