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
@CrossOrigin("*")
public class OrderController {

    private final OrderFacade orderFacade;

    @GetMapping("getAllOrders")
    public List<OrderDto> getAllOrders() {
        return orderFacade.getAllOrders();
    }

    @GetMapping("getOrderById")
    public OrderDto getOrderById(@RequestParam int orderId) {
        return orderFacade.getOrderById(orderId);
    }

    @GetMapping("getOrderByName")
    public OrderDto getOrderByName(@RequestParam String orderName) {
        return orderFacade.getOrderByName(orderName);
    }

    @PostMapping(value = "createOrder", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createOrder(@RequestBody OrderDto orderDto) {
        orderFacade.createNewOrder(orderDto);
    }

    @DeleteMapping("deleteOrder")
    public void deleteOrder(@RequestParam int orderId) {
        orderFacade.deleteOrder(orderId);
    }

    @PutMapping("updateOrder")
    public OrderDto updateOrder(@RequestBody OrderDto orderDto) {
        return orderFacade.updateOrder(orderDto);
    }
}
