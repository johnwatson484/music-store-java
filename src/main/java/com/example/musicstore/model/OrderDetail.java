package com.example.musicstore.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantity;

    private BigDecimal unitPrice;

    @ManyToOne(optional = false)
    private Album album;

    @ManyToOne(optional = false)
    private Order order;

    public OrderDetail() {
    }

    public OrderDetail(Album album, int quantity, BigDecimal unitPrice) {
        this.album = album;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public Long getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public Album getAlbum() {
        return album;
    }

    public Order getOrder() {
        return order;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
