package com.example.store.controller;

import com.example.store.model.CartItem;
import com.example.store.service.CartService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
@CrossOrigin(origins = "*")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public List<CartItem> getCart() {
        return cartService.getCartItems();
    }

    @PostMapping("/add/{productId}")
    public CartItem addItem(@PathVariable Long productId, @RequestParam(defaultValue = "1") Integer quantity) {
        return cartService.addToCart(productId, quantity);
    }

    @DeleteMapping("/clear")
    public void clear() {
        cartService.clearCart();
    }

    @PostMapping("/remove/{cartItemId}")
    public void removeItem(@PathVariable Long cartItemId) {
        cartService.removeFromCart(cartItemId);
    }
}