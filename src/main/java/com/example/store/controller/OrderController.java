package com.example.store.controller;

import com.example.store.model.Order;
import com.example.store.service.OrderService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/checkout")
    public Order placeOrder() {
        return orderService.checkout();
    }
}