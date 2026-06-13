package com.example.musicstore.controller;

import com.example.musicstore.service.ShoppingCartService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping
    public String index(HttpSession session, Model model) {
        String cartId = session.getId();

        model.addAttribute("items", shoppingCartService.getItems(cartId));
        model.addAttribute("total", shoppingCartService.getTotal(cartId));

        return "cart/index";
    }

    @GetMapping("/add/{albumId}")
    public String addToCart(@PathVariable Long albumId, HttpSession session) {
        shoppingCartService.addToCart(session.getId(), albumId);
        return "redirect:/cart";
    }

    @PostMapping("/remove/{itemId}")
    public String removeFromCart(@PathVariable Long itemId, HttpSession session) {
        shoppingCartService.removeOne(session.getId(), itemId);
        return "redirect:/cart";
    }
}
