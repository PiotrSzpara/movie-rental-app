package com.crud.movies.mapper;

import com.crud.movies.domain.Movie;
import com.crud.movies.domain.MovieDto;
import com.crud.movies.domain.Order;
import com.crud.movies.domain.OrderDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderMapper {

    public Order mapToOrder(final OrderDto orderDto) {
        return new Order(
                orderDto.getOrderId(),
                orderDto.getOrderName(),
                orderDto.isPaid(),
                orderDto.getOrderDate(),
                orderDto.getOrderDateEnd()
        );
    }

    public OrderDto mapToOrderDto(final Order order) {
        return new OrderDto(
                order.getOrderId(),
                order.getOrderName(),
                order.isPaid(),
                order.getOrderDate(),
                order.getOrderDateEnd()
        );
    }

    public List<OrderDto> mapToOrderDtoList(final List<Order> orders) {
        return orders.stream()
                .map(this::mapToOrderDto)
                .collect(Collectors.toList());
    }
}
