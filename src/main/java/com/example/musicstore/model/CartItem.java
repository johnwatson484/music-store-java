package com.example.musicstore.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cartId;

    @ManyToOne(optional = false)
    private Album album;

    private int count;

    private LocalDateTime dateCreated;

    public CartItem() {
    }

    public CartItem(String cartId, Album album, int count) {
        this.cartId = cartId;
        this.album = album;
        this.count = count;
        this.dateCreated = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getCartId() {
        return cartId;
    }

    public Album getAlbum() {
        return album;
    }

    public int getCount() {
        return count;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }
}
