package com.crud.movies.service;

import com.crud.movies.domain.Order;
import com.crud.movies.domain.OrderDao;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderDbService {
    @Autowired
    private final OrderDao orderDao;

    public List<Order> getAllOrders() {
        return orderDao.findAll();
    }

    public Order getOrderById(final int orderId) {
        return orderDao.findById(orderId);
    }

    public Order getOrderByName(final String orderName) {
        return orderDao.findByName(orderName);
    }

    public Order saveOrder(Order order) {
        return orderDao.save(order);
    }

    public void deleteOrderById(int orderId) {
        orderDao.deleteById(orderId);
    }
}
