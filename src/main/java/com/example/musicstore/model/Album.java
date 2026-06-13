package com.example.musicstore.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Entity
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String title;

    @NotNull
    @DecimalMin("0.00")
    @Column(nullable = false)
    private BigDecimal price;

    private String albumArtUrl;

    @ManyToOne(optional = false)
    private Genre genre;

    @ManyToOne(optional = false)
    private Artist artist;

    public Album() {
    }

    public Album(String title, BigDecimal price, String albumArtUrl, Genre genre, Artist artist) {
        this.title = title;
        this.price = price;
        this.albumArtUrl = albumArtUrl;
        this.genre = genre;
        this.artist = artist;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getAlbumArtUrl() {
        return albumArtUrl;
    }

    public Genre getGenre() {
        return genre;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setAlbumArtUrl(String albumArtUrl) {
        this.albumArtUrl = albumArtUrl;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }
}
