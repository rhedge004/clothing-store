package com.example.store.repository;

import com.example.store.model.Product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
    List<Product> findByCategoryIgnoreCase(String category);

    List<Product> findByTypeIgnoreCase(String type);

    List<Product> findByTypeIgnoreCaseAndCategoryIgnoreCase(String type, String category);

    @Query("SELECT DISTINCT p.category FROM Product p")
    List<String> findDistinctCategories();
}