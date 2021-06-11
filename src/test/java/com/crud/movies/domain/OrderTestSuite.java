package com.crud.movies.domain;

import com.crud.movies.repository.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderTestSuite {

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RentRepository rentRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testOrderRepositorySave() {
        //Given
        Order order = new Order();

        //When
        orderRepository.deleteAll();
        orderRepository.save(order);

        //Then
        List<Order> orders = orderRepository.findAll();
        assertEquals(1, orders.size());

        //CleanUp
        orderRepository.deleteAll();
    }

    @Test
    public void testOrderRepositoryDelete() {
        //Given
        Order order = new Order();

        //When
        orderRepository.deleteAll();
        orderRepository.save(order);
        orderRepository.delete(order);

        //Then
        List<Order> orders = orderRepository.findAll();
        assertEquals(0, orders.size());

        //CleanUp
        orderRepository.deleteAll();
    }

    @Test
    public void testCreateNewOrder() {
        //Given
        Order order = new Order();

        //When
        orderRepository.save(order);

        //Then
        int id = order.getOrderId();
        Order readOrder = orderRepository.findByOrderId(id);
        assertEquals(readOrder.getOrderId(),id);

        //CleanUp
        orderRepository.deleteById(id);
    }
}
