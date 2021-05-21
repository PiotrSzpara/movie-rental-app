package com.crud.movies.controller;

import com.crud.movies.domain.MovieDto;
import com.crud.movies.domain.OrderDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/order")
public class OrderController {

    @RequestMapping(method = RequestMethod.GET, value = "getAllOrders")
    public List<OrderDto> getAllOrders() {
        return new ArrayList();
    }

    @RequestMapping(method = RequestMethod.GET, value = "getOrder")
    public OrderDto getOrder(@RequestParam int orderId) {
        return new OrderDto();
    }

    @RequestMapping(method = RequestMethod.POST, value = "createOrder", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createOrder(@RequestBody OrderDto orderDto) {

    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteOrder")
    public void deleteOrder(@RequestParam int orderId) {

    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateOrder")
    public void updateOrder(@RequestParam int orderId) {

    }
}
