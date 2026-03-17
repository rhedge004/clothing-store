package com.example.clothing.service;

import com.example.store.model.CartItem;
import com.example.store.model.Product;
import com.example.store.repository.CartRepository;
import com.example.store.repository.ProductRepository;
import com.example.store.service.CartService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CartServiceTest {

    @Mock
    private CartRepository cartRepository;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private CartService cartService;

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setId(1L);
        product.setName("Test Product");
        product.setPrice(BigDecimal.valueOf(50.00));
        product.setStockQuantity(100);
    }

    @Test
    void addToCart_ProductNotFound() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            cartService.addToCart(1L, 1);
        });

        assertEquals("Product not found", exception.getMessage());
        verify(cartRepository, never()).save(any());
    }

    @Test
    void addToCart_NewItem() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(cartRepository.findByProduct(product)).thenReturn(null);
        
        when(cartRepository.save(any(CartItem.class))).thenAnswer(invocation -> invocation.getArgument(0));

        CartItem result = cartService.addToCart(1L, 2);

        assertNotNull(result);
        assertEquals(product, result.getProduct());
        assertEquals(2, result.getQuantity());
        verify(cartRepository).save(any(CartItem.class));
    }

    @Test
    void addToCart_ExistingItem() {
        CartItem existingItem = new CartItem();
        existingItem.setId(10L);
        existingItem.setProduct(product);
        existingItem.setQuantity(1);

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(cartRepository.findByProduct(product)).thenReturn(existingItem);
        when(cartRepository.save(existingItem)).thenReturn(existingItem);

        CartItem result = cartService.addToCart(1L, 2);

        assertEquals(3, result.getQuantity()); 
        verify(cartRepository).save(existingItem);
    }

    @Test
    void getCartItems() {
        CartItem item1 = new CartItem();
        CartItem item2 = new CartItem();
        when(cartRepository.findAll()).thenReturn(Arrays.asList(item1, item2));

        List<CartItem> result = cartService.getCartItems();

        assertEquals(2, result.size());
        verify(cartRepository).findAll();
    }

    @Test
    void clearCart() {
        cartService.clearCart();

        verify(cartRepository).deleteAll();
    }

    @Test
    void removeFromCart() {
        when(cartRepository.existsById(10L)).thenReturn(true);

        cartService.removeFromCart(10L);

        verify(cartRepository).deleteById(10L);
    }

    @Test
    void removeFromCart_NotFound() {
        when(cartRepository.existsById(10L)).thenReturn(false);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            cartService.removeFromCart(10L);
        });

        assertEquals("Cart item not found with id: 10", exception.getMessage());
        verify(cartRepository, never()).deleteById(any());
    }
}