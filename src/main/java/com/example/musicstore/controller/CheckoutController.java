package com.example.musicstore.controller;

import com.example.musicstore.model.Order;
import com.example.musicstore.service.CheckoutService;
import com.example.musicstore.service.ShoppingCartService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {

    private final ShoppingCartService shoppingCartService;
    private final CheckoutService checkoutService;

    public CheckoutController(
            ShoppingCartService shoppingCartService,
            CheckoutService checkoutService) {
        this.shoppingCartService = shoppingCartService;
        this.checkoutService = checkoutService;
    }

    @GetMapping
    public String checkout(HttpSession session, Model model) {
        if (shoppingCartService.getItems(session.getId()).isEmpty()) {
            return "redirect:/cart";
        }

        model.addAttribute("order", new Order());
        model.addAttribute("total", shoppingCartService.getTotal(session.getId()));
        return "checkout/index";
    }

    @PostMapping
    public String placeOrder(
            @Valid @ModelAttribute Order order,
            BindingResult bindingResult,
            HttpSession session,
            Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("total", shoppingCartService.getTotal(session.getId()));
            return "checkout/index";
        }

        Order savedOrder = checkoutService.createOrder(session.getId(), order);
        return "redirect:/checkout/complete/" + savedOrder.getId();
    }

    @GetMapping("/complete/{orderId}")
    public String complete(@PathVariable Long orderId, Model model) {
        model.addAttribute("orderId", orderId);
        return "checkout/complete";
    }
}
