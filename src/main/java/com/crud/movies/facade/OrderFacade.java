package com.crud.movies.facade;

import com.crud.movies.domain.Order;
import com.crud.movies.domain.OrderDto;
import com.crud.movies.mapper.OrderMapper;
import com.crud.movies.service.OrderDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
public class OrderFacade {

    private final OrderDbService orderDbService;
    private final OrderMapper orderMapper;

    @GetMapping(value = "getAllOrders")
    public List<OrderDto> getAllOrders() {
        List<Order> orders = orderDbService.getAllOrders();
        return orderMapper.mapToOrderDtoList(orders);
    }

    @GetMapping(value = "getOrderById")
    public OrderDto getOrderById(@RequestParam int orderId) {
        Order order = orderDbService.getOrderById(orderId);
        return orderMapper.mapToOrderDto(order);
    }

    @GetMapping(value = "getOrderByName")
    public OrderDto getOrderByName(@RequestParam String orderName) {
        Order order = orderDbService.getOrderByName(orderName);
        return orderMapper.mapToOrderDto(order);
    }

    @PostMapping(value = "createOrder", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createOrder(@RequestBody OrderDto orderDto) {
        Order newOrder = orderMapper.mapToOrder(orderDto);
        orderDbService.saveOrder(newOrder);
    }

    @DeleteMapping(value = "deleteOrder")
    public void deleteOrder(@RequestParam int orderId) {
        orderDbService.deleteOrderById(orderId);
    }

    @PutMapping(value = "updateOrder")
    public OrderDto updateOrder(@RequestBody OrderDto orderDto) {
        Order order = orderMapper.mapToOrder(orderDto);
        Order updatedOrder = orderDbService.saveOrder(order);
        return orderMapper.mapToOrderDto(updatedOrder);
    }
}
