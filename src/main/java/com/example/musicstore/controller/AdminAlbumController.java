package com.example.musicstore.controller;

import com.example.musicstore.model.Album;
import com.example.musicstore.repository.AlbumRepository;
import com.example.musicstore.repository.ArtistRepository;
import com.example.musicstore.repository.GenreRepository;
import com.example.musicstore.repository.CartItemRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/albums")
public class AdminAlbumController {

    private final AlbumRepository albumRepository;
    private final GenreRepository genreRepository;
    private final ArtistRepository artistRepository;
    private final CartItemRepository cartItemRepository;

    public AdminAlbumController(
            AlbumRepository albumRepository,
            GenreRepository genreRepository,
            ArtistRepository artistRepository,
            CartItemRepository cartItemRepository) {
        this.albumRepository = albumRepository;
        this.genreRepository = genreRepository;
        this.artistRepository = artistRepository;
        this.cartItemRepository = cartItemRepository;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("albums", albumRepository.findAll());
        return "admin/albums/index";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("album", new Album());
        addFormLists(model);
        return "admin/albums/form";
    }

    @PostMapping
    public String create(
            @Valid @ModelAttribute Album album,
            BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            addFormLists(model);
            return "admin/albums/form";
        }

        albumRepository.save(album);
        return "redirect:/admin/albums";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Album album = albumRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Album not found: " + id));

        model.addAttribute("album", album);
        addFormLists(model);
        return "admin/albums/form";
    }

    @PostMapping("/edit/{id}")
    public String edit(
            @PathVariable Long id,
            @Valid @ModelAttribute Album album,
            BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            addFormLists(model);
            return "admin/albums/form";
        }

        album.setId(id);
        albumRepository.save(album);
        return "redirect:/admin/albums";
    }

    @GetMapping("/delete/{id}")
    public String deleteConfirm(@PathVariable Long id, Model model) {
        Album album = albumRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Album not found: " + id));

        model.addAttribute("album", album);
        return "admin/albums/delete";
    }

    @PostMapping("/delete/{id}")
    @Transactional
    public String delete(@PathVariable Long id) {
        Album album = albumRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Album not found: " + id));

        cartItemRepository.deleteByAlbum(album);
        albumRepository.delete(album);

        return "redirect:/admin/albums";
    }

    private void addFormLists(Model model) {
        model.addAttribute("genres", genreRepository.findAll());
        model.addAttribute("artists", artistRepository.findAll());
    }
}
