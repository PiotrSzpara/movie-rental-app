package com.crud.movies.controller;

import com.crud.movies.domain.Order;
import com.crud.movies.domain.OrderDto;
import com.crud.movies.mapper.OrderMapper;
import com.crud.movies.service.OrderDbService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/order")
public class OrderController {
    private final OrderDbService orderDbService;
    private final OrderMapper orderMapper;

    public OrderController(OrderDbService orderDbService, OrderMapper orderMapper) {
        this.orderDbService = orderDbService;
        this.orderMapper = orderMapper;
    }


    @RequestMapping(method = RequestMethod.GET, value = "getAllOrders")
    public List<OrderDto> getAllOrders() {
        List<Order> orders = orderDbService.getAllOrders();
        return orderMapper.mapToOrderDtoList(orders);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getOrderById")
    public OrderDto getOrderById(@RequestParam int orderId) {
        Order order = orderDbService.getOrderById(orderId);
        return orderMapper.mapToOrderDto(order);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getOrderByName")
    public OrderDto getOrderByName(@RequestParam String orderName) {
        Order order = orderDbService.getOrderByName(orderName);
        return orderMapper.mapToOrderDto(order);
    }

    @RequestMapping(method = RequestMethod.POST, value = "createOrder", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createOrder(@RequestBody OrderDto orderDto) {
        Order newOrder = orderMapper.mapToOrder(orderDto);
        orderDbService.saveOrder(newOrder);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteOrder")
    public void deleteOrder(@RequestParam int orderId) {
        orderDbService.deleteOrderById(orderId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateOrder")
    public OrderDto updateOrder(@RequestBody OrderDto orderDto) {
        Order order = orderMapper.mapToOrder(orderDto);
        Order updatedOrder = orderDbService.saveOrder(order);
        return orderMapper.mapToOrderDto(updatedOrder);
    }
}
