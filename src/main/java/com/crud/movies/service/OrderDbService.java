package com.crud.movies.service;

import com.crud.movies.domain.Order;
import com.crud.movies.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderDbService {

    private final OrderRepository orderRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(final int orderId) {
        return orderRepository.findByOrderId(orderId);
    }

    public Order getOrderByName(final String orderName) {
        return orderRepository.findByOrderName(orderName);
    }

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    public void deleteOrderById(int orderId) {
        orderRepository.deleteById(orderId);
    }
}
