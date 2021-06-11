package com.crud.movies.mapper;

import com.crud.movies.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMapperTestSuite {

    @Autowired
    private OrderMapper orderMapper;

    @Test
    public void testMapToOrder() {
        //Given
        Order order = new Order(1, "orderNameTest", true, LocalDate.of(2021,06,05), LocalDate.of(2021,06,06));
        OrderDto orderDto = new OrderDto(1, "orderNameTest", true, LocalDate.of(2021,06,05), LocalDate.of(2021,06,06));
        //When
        Order mappedOrder = orderMapper.mapToOrder(orderDto);

        //Then
        assertEquals(orderDto.getOrderId(),mappedOrder.getOrderId());
        assertEquals(orderDto.getOrderName(),mappedOrder.getOrderName());
        assertEquals(orderDto.getOrderDate(),mappedOrder.getOrderDate());
        assertEquals(orderDto.getOrderDateEnd(),mappedOrder.getOrderDateEnd());
    }

    @Test
    public void testMapToMovieDto() {
        Order order = new Order(1, "orderNameTest", true, LocalDate.of(2021,06,05), LocalDate.of(2021,06,06));
        OrderDto orderDto = new OrderDto(1, "orderNameTest", true, LocalDate.of(2021,06,05), LocalDate.of(2021,06,06));
        //When
        OrderDto mappedOrder = orderMapper.mapToOrderDto(order);

        //Then
        assertEquals(order.getOrderId(),mappedOrder.getOrderId());
        assertEquals(order.getOrderName(),mappedOrder.getOrderName());
        assertEquals(order.getOrderDate(),mappedOrder.getOrderDate());
        assertEquals(order.getOrderDateEnd(),mappedOrder.getOrderDateEnd());
    }

    @Test
    public void testMapToMovieDtoList() {
        //Given
        Order order = new Order(1, "orderNameTest", true, LocalDate.of(2021,06,05), LocalDate.of(2021,06,06));
        List<Order> orderList = new ArrayList<>();
        orderList.add(order);

        OrderDto orderDto = new OrderDto(1, "orderNameTest", true, LocalDate.of(2021,06,05), LocalDate.of(2021,06,06));
        List<OrderDto> orderDtoList = new ArrayList<>();
        orderDtoList.add(orderDto);

        //When
        List<OrderDto> mappedOrderDtoList = orderMapper.mapToOrderDtoList(orderList);

        //Then
        assertEquals(orderDtoList.size(),mappedOrderDtoList.size());
        assertEquals(orderDtoList.get(0).getOrderId(),mappedOrderDtoList.get(0).getOrderId());
        assertEquals(orderDtoList.get(0).getOrderName(),mappedOrderDtoList.get(0).getOrderName());
        assertEquals(orderDtoList.get(0).getOrderDate(),mappedOrderDtoList.get(0).getOrderDate());
        assertEquals(orderDtoList.get(0).getOrderDateEnd(),mappedOrderDtoList.get(0).getOrderDateEnd());
    }
}
