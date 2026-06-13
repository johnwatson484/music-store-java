package com.example.musicstore.repository;

import com.example.musicstore.model.Album;
import com.example.musicstore.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByCartId(String cartId);

    Optional<CartItem> findByCartIdAndAlbum(String cartId, Album album);

    void deleteByCartId(String cartId);

    void deleteByAlbum(Album album);
}
