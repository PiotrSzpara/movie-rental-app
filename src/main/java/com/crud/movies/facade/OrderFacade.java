package com.crud.movies.facade;

import com.crud.movies.domain.Order;
import com.crud.movies.domain.OrderDto;
import com.crud.movies.mapper.OrderMapper;
import com.crud.movies.service.OrderDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Component
public class OrderFacade {

    private final OrderDbService orderDbService;
    private final OrderMapper orderMapper;

    public List<OrderDto> getAllOrders() {
        List<Order> orders = orderDbService.getAllOrders();
        return orderMapper.mapToOrderDtoList(orders);
    }

    public OrderDto getOrderById(@RequestParam int orderId) {
        Order order = orderDbService.getOrderById(orderId);
        return orderMapper.mapToOrderDto(order);
    }

    public OrderDto getOrderByName(@RequestParam String orderName) {
        Order order = orderDbService.getOrderByName(orderName);
        return orderMapper.mapToOrderDto(order);
    }

    public void createNewOrder(@RequestBody OrderDto orderDto) {
        Order newOrder = orderMapper.mapToOrder(orderDto);
        orderDbService.saveOrder(newOrder);
    }

    public void deleteOrder(@RequestParam int orderId) {
        orderDbService.deleteOrderById(orderId);
    }

    public OrderDto updateOrder(@RequestBody OrderDto orderDto) {
        Order order = orderMapper.mapToOrder(orderDto);
        Order updatedOrder = orderDbService.saveOrder(order);
        return orderMapper.mapToOrderDto(updatedOrder);
    }
}
