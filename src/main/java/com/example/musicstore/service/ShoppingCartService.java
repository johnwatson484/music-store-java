package com.example.musicstore.service;

import com.example.musicstore.model.Album;
import com.example.musicstore.model.CartItem;
import com.example.musicstore.repository.AlbumRepository;
import com.example.musicstore.repository.CartItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ShoppingCartService {

    private final CartItemRepository cartItemRepository;
    private final AlbumRepository albumRepository;

    public ShoppingCartService(CartItemRepository cartItemRepository, AlbumRepository albumRepository) {
        this.cartItemRepository = cartItemRepository;
        this.albumRepository = albumRepository;
    }

    public List<CartItem> getItems(String cartId) {
        return cartItemRepository.findByCartId(cartId);
    }

    public void addToCart(String cartId, Long albumId) {
        Album album = albumRepository.findById(albumId)
                .orElseThrow(() -> new IllegalArgumentException("Album not found: " + albumId));

        CartItem item = cartItemRepository.findByCartIdAndAlbum(cartId, album)
                .orElse(new CartItem(cartId, album, 0));

        item.setCount(item.getCount() + 1);
        cartItemRepository.save(item);
    }

    public void removeOne(String cartId, Long itemId) {
        CartItem item = cartItemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("Cart item not found: " + itemId));

        if (!item.getCartId().equals(cartId)) {
            throw new IllegalArgumentException("Cart item does not belong to this cart");
        }

        if (item.getCount() <= 1) {
            cartItemRepository.delete(item);
        } else {
            item.setCount(item.getCount() - 1);
            cartItemRepository.save(item);
        }
    }

    public BigDecimal getTotal(String cartId) {
        return getItems(cartId).stream()
                .map(item -> item.getAlbum().getPrice().multiply(BigDecimal.valueOf(item.getCount())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Transactional
    public void clearCart(String cartId) {
        cartItemRepository.deleteByCartId(cartId);
    }
}
