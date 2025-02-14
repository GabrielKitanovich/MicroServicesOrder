package com.gabriel.microservices.order_service.service;

import java.util.UUID;
import java.util.List;

import org.springframework.stereotype.Service;

import com.gabriel.microservices.order_service.dto.OrderRequest;
import com.gabriel.microservices.order_service.model.Order;
import com.gabriel.microservices.order_service.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setSkuCode(orderRequest.skuCode());
        order.setPrice(orderRequest.price());
        order.setQuantity(orderRequest.quantity());
        orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
