package com.example.store.repository;

import com.example.store.model.CartItem;
import com.example.store.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<CartItem, Long> {
    CartItem findByProduct(Product product);
}