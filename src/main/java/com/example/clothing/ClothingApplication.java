package com.example.clothing;

import com.example.store.model.Product;
import com.example.store.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

@SpringBootApplication(scanBasePackages = "com.example")
@EnableJpaRepositories(basePackages = "com.example.store.repository")
@EntityScan(basePackages = "com.example.store.model")
public class ClothingApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClothingApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(ProductRepository repository) {
        return args -> {
            Product p1 = new Product(null, "Vintage Denim Jacket", "Classic blue denim", 89.99, "Outerwear", 15, "L");
            Product p2 = new Product(null, "Cotton T-Shirt", "100% organic cotton", 24.50, "Basics", 50, "M");
            Product p3 = new Product(null, "Slim Fit Chinos", "Stretch fabric trousers", 55.00, "Pants", 20, "M");

            repository.saveAll(List.of(p1, p2, p3));
            System.out.println("Database populated with sample clothing items.");
        };
    }
}