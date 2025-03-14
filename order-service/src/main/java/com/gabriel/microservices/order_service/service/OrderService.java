package com.gabriel.microservices.order_service.service;
import com.gabriel.microservices.order_service.client.InventoryClient;

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
    private final InventoryClient inventoryClient;


    public void placeOrder(OrderRequest orderRequest) {

        var isProductInStock = inventoryClient.isInStock(orderRequest.skuCode(), orderRequest.quantity());

        if (isProductInStock) {
            Order order = new Order();
            order.setOrderNumber(UUID.randomUUID().toString());
            order.setSkuCode(orderRequest.skuCode());
            order.setPrice(orderRequest.price());
            order.setQuantity(orderRequest.quantity());
            orderRepository.save(order);
        } else {
            throw new RuntimeException("Product " + orderRequest.skuCode() + " is out of stock");
        }

        
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
