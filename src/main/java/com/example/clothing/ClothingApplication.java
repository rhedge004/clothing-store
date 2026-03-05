package com.example.clothing;

import com.example.store.model.Product;
import com.example.store.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.math.BigDecimal;
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
            Product p1 = new Product();
            p1.setName("Vintage Denim Jacket");
            p1.setDescription("Classic blue denim");
            p1.setPrice(BigDecimal.valueOf(89.99));
            p1.setCategory("Outerwear");
            p1.setStockQuantity(15);
            p1.setSize("M");

            Product p2 = new Product();
            p2.setName("Cotton T-Shirt");
            p2.setDescription("100% organic cotton");
            p2.setPrice(BigDecimal.valueOf(24.50));
            p2.setCategory("Basics");
            p2.setStockQuantity(50);
            p2.setSize("L");

            Product p3 = new Product();
            p3.setName("Slim Fit Chinos");
            p3.setDescription("Stretch fabric trousers");
            p3.setPrice(BigDecimal.valueOf(55.00));
            p3.setCategory("Pants");
            p3.setStockQuantity(20);
            p3.setSize("32");

            repository.saveAll(List.of(p1, p2, p3));
            System.out.println("Database populated with sample clothing items.");
        };
    }
}