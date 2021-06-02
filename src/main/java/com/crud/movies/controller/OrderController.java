package com.crud.movies.controller;

import com.crud.movies.domain.OrderDto;
import com.crud.movies.facade.OrderFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/order")
@RequiredArgsConstructor
public class OrderController {

    private OrderFacade orderFacade;

    @GetMapping(value = "getAllOrders")
    public List<OrderDto> getAllOrders() {
        return orderFacade.getAllOrders();
    }

    @GetMapping(value = "getOrderById")
    public OrderDto getOrderById(@RequestParam int orderId) {
        return orderFacade.getOrderById(orderId);
    }

    @GetMapping(value = "getOrderByName")
    public OrderDto getOrderByName(@RequestParam String orderName) {
        return orderFacade.getOrderByName(orderName);
    }

    @PostMapping(value = "createOrder", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createOrder(@RequestBody OrderDto orderDto) {
        orderFacade.createOrder(orderDto);
    }

    @DeleteMapping(value = "deleteOrder")
    public void deleteOrder(@RequestParam int orderId) {
        orderFacade.deleteOrder(orderId);
    }

    @PutMapping(value = "updateOrder")
    public OrderDto updateOrder(@RequestBody OrderDto orderDto) {
        return orderFacade.updateOrder(orderDto);
    }
}
