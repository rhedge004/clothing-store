package com.example.store.service;

import com.example.store.model.CartItem;
import com.example.store.model.Product;
import com.example.store.repository.CartRepository;
import com.example.store.repository.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public CartService(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    public CartItem addToCart(Long productId, Integer quantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        
        CartItem existingItem = cartRepository.findByProduct(product);
        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
            return cartRepository.save(existingItem);
        } else {
            CartItem newItem = new CartItem();
            newItem.setProduct(product);
            newItem.setQuantity(quantity);
            return cartRepository.save(newItem);
        }
    }

    public List<CartItem> getCartItems() {
        return cartRepository.findAll();
    }

    public void clearCart() {
        cartRepository.deleteAll();
    }

    public void removeFromCart(Long cartItemId) {
    if (!cartRepository.existsById(cartItemId)) {
        throw new RuntimeException("Cart item not found with id: " + cartItemId);
    }
    cartRepository.deleteById(cartItemId);
}
}