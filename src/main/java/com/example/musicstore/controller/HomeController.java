package com.example.musicstore.controller;

import com.example.musicstore.repository.AlbumRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final AlbumRepository albumRepository;

    public HomeController(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("albums", albumRepository.findTop6ByOrderByIdDesc());
        return "home/index";
    }
}
