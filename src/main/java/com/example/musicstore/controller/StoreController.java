package com.example.musicstore.controller;

import com.example.musicstore.model.Genre;
import com.example.musicstore.repository.AlbumRepository;
import com.example.musicstore.repository.GenreRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/store")
public class StoreController {

    private final GenreRepository genreRepository;
    private final AlbumRepository albumRepository;

    public StoreController(GenreRepository genreRepository, AlbumRepository albumRepository) {
        this.genreRepository = genreRepository;
        this.albumRepository = albumRepository;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("genres", genreRepository.findAll());
        return "store/index";
    }

    @GetMapping("/browse")
    public String browse(@RequestParam String genre, Model model) {
        Genre selectedGenre = genreRepository.findByNameIgnoreCase(genre)
                .orElseThrow(() -> new IllegalArgumentException("Genre not found: " + genre));

        model.addAttribute("genre", selectedGenre);
        model.addAttribute("albums", albumRepository.findByGenreOrderByTitleAsc(selectedGenre));
        return "store/browse";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        var album = albumRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Album not found: " + id));

        model.addAttribute("album", album);
        return "store/details";
    }
}
