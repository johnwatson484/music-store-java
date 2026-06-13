package com.example.musicstore.repository;

import com.example.musicstore.model.Album;
import com.example.musicstore.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlbumRepository extends JpaRepository<Album, Long> {
    List<Album> findByGenreOrderByTitleAsc(Genre genre);

    List<Album> findTop6ByOrderByIdDesc();
}
