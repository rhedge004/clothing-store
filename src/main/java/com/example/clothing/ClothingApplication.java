package com.example.clothing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@ComponentScan(basePackages = { "com.example.store", "com.example.clothing" })
@EntityScan(basePackages = { "com.example.store.model" })
@EnableJpaRepositories(basePackages = { "com.example.store.repository" })
public class ClothingApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClothingApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}