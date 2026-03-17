package com.example.store.controller;

import com.example.store.model.Product;
import com.example.store.repository.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "*")
public class ProductController {

    private final ProductRepository repository;

    public ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return repository.save(product);
    }

    @GetMapping("/filter")
    public List<Product> filterProducts(@RequestParam(required = false) String type,
            @RequestParam(required = false) String category) {
        if (type != null && category != null) {
            return repository.findByTypeIgnoreCaseAndCategoryIgnoreCase(type, category);
        } else if (type != null) {
            return repository.findByTypeIgnoreCase(type);
        } else if (category != null) {
            return repository.findByCategoryIgnoreCase(category);
        }
        return repository.findAll();
    }

    @GetMapping("/categories")
    public List<String> getCategories() {
        return repository.findDistinctCategories();
    }
}