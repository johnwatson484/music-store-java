package com.example.musicstore.service;

import com.example.musicstore.model.Order;
import com.example.musicstore.model.OrderDetail;
import com.example.musicstore.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CheckoutService {

    private final ShoppingCartService shoppingCartService;
    private final OrderRepository orderRepository;

    public CheckoutService(ShoppingCartService shoppingCartService, OrderRepository orderRepository) {
        this.shoppingCartService = shoppingCartService;
        this.orderRepository = orderRepository;
    }

    @Transactional
    public Order createOrder(String cartId, Order order) {
        var cartItems = shoppingCartService.getItems(cartId);

        if (cartItems.isEmpty()) {
            throw new IllegalStateException("Cannot checkout with an empty cart");
        }

        BigDecimal total = BigDecimal.ZERO;

        for (var item : cartItems) {
            var detail = new OrderDetail(
                    item.getAlbum(),
                    item.getCount(),
                    item.getAlbum().getPrice());

            order.addOrderDetail(detail);

            total = total.add(
                    item.getAlbum().getPrice().multiply(BigDecimal.valueOf(item.getCount())));
        }

        order.setTotal(total);

        Order savedOrder = orderRepository.save(order);
        shoppingCartService.clearCart(cartId);

        return savedOrder;
    }
}
