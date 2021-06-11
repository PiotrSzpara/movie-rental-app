package com.crud.movies.service;

import com.crud.movies.domain.Order;
import com.crud.movies.repository.OrderRepository;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class OrderDbServiceTestSuite {

    @InjectMocks
    private OrderDbService orderDbService;

    @Mock
    private OrderRepository orderRepository;


    @Test
    public void getAllOrdersTest() {
        //Given
        Order order = new Order(1, "orderNameTest",true, LocalDate.of(2021,06,05), LocalDate.of(2021,06,06));

        List<Order> orderList = new ArrayList<>();
        orderList.add(order);

        when(orderRepository.findAll()).thenReturn(orderList);

        //When
        List<Order> theList = orderDbService.getAllOrders();

        //Then
        assertEquals(1, theList.size());
        assertNotNull(theList);
    }
    @Test
    public void getOrderTest() {
        //Given
        Order order = new Order(1, "orderNameTest",true, LocalDate.of(2021,06,05), LocalDate.of(2021,06,06));


        List<Order> orderList = new ArrayList<>();
        orderList.add(order);

        when(orderRepository.findByOrderId(1)).thenReturn(order);

        //When
        Order theOrder = orderDbService.getOrderById(1);

        //Then
        assertEquals(order, theOrder);
    }

    @Test
    public void saveOrderTest() {
        //Given
        Order order = new Order(1, "orderNameTest",true, LocalDate.of(2021,06,05), LocalDate.of(2021,06,06));

        when(orderRepository.save(order)).thenReturn(order);

        //When
        Order savedOrder = orderDbService.saveOrder(order);

        //Then
        assertEquals(order.getOrderId(), savedOrder.getOrderId());
        assertEquals(order.getOrderName(), savedOrder.getOrderName());
        assertEquals(order.getOrderDate(), savedOrder.getOrderDate());
        assertEquals(order.getOrderDateEnd(), savedOrder.getOrderDateEnd());
        assertEquals(order.getRent(), savedOrder.getRent());
    }

    @Test
    public void deleteOrderTest() {
        //Given
        Order order = new Order(1, "orderNameTest",true, LocalDate.of(2021,06,05), LocalDate.of(2021,06,06));

        int id = order.getOrderId();

        orderDbService.deleteOrderById(id);

        //When
        Order orderToDelete = orderDbService.getOrderById(id);
        //Then
        assertNull(orderToDelete);
    }
}
