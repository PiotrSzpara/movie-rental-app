package com.crud.movies.controller;

import com.crud.movies.domain.*;
import com.crud.movies.facade.OrderFacade;
import com.crud.movies.mapper.OrderMapper;
import com.crud.movies.service.OrderDbService;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitWebConfig
@RunWith(SpringRunner.class)
@WebMvcTest(OrderController.class)
public class OrderControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderDbService orderDbService;

    @MockBean
    private OrderFacade orderFacade;

    @MockBean
    private OrderMapper orderMapper;

    @Test
    public void getAllOrdersEmptyTest() throws Exception {
        // Given
        List<OrderDto> orders = new ArrayList<>();
        when(orderFacade.getAllOrders()).thenReturn(orders);

        //When & Then
        mockMvc
                .perform(get("/v1/order/getAllOrders")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200)) // or isOk()
                .andExpect(jsonPath("$", Matchers.hasSize(0)));
    }

    @Test
    public void getAllOrdersTest() throws Exception {
        // Given
        Order order = new Order(1, "orderNameTest",true, LocalDate.of(2021,06,05), LocalDate.of(2021,06,06));
        OrderDto orderDto = new OrderDto(1, "orderNameTest",true, LocalDate.of(2021,06,05), LocalDate.of(2021,06,06));

        List<OrderDto> orders = new ArrayList<>();
        orders.add(orderDto);

        when(orderFacade.getAllOrders()).thenReturn(orders);

        //When & Then
        mockMvc
                .perform(get("/v1/order/getAllOrders")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200)) // or isOk()
                .andExpect(jsonPath("$", Matchers.hasSize(1)));
    }

    @Test
    public void getOrderTest() throws Exception {
        // Given
        Order order = new Order(1, "orderNameTest",true, LocalDate.of(2021,06,05), LocalDate.of(2021,06,06));
        OrderDto orderDto = new OrderDto(1, "orderNameTest",true, LocalDate.of(2021,06,05), LocalDate.of(2021,06,06));


        when(orderFacade.getOrderById(1)).thenReturn(orderDto);
        when(orderMapper.mapToOrder(orderDto)).thenReturn(order);

        //When & Then
        mockMvc
                .perform(get("/v1/order/getOrderById?orderId=1")
                        .param("orderId", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.orderId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.orderName", Matchers.is("orderNameTest")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.orderDate", Matchers.is("2021-06-05")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.orderDateEnd", Matchers.is("2021-06-06")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.paid", Matchers.is(true)))
        ;
    }

    @Test
    public void getOrderByNameTest() throws Exception {
        // Given
        Order order = new Order(1, "orderNameTest",true, LocalDate.of(2021,06,05), LocalDate.of(2021,06,06));
        OrderDto orderDto = new OrderDto(1, "orderNameTest",true, LocalDate.of(2021,06,05), LocalDate.of(2021,06,06));


        when(orderFacade.getOrderByName("orderNameTest")).thenReturn(orderDto);
        when(orderMapper.mapToOrder(orderDto)).thenReturn(order);

        //When & Then
        mockMvc
                .perform(get("/v1/order/getOrderByName")
                        .param("orderName", "orderNameTest")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.orderId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.orderName", Matchers.is("orderNameTest")))
        ;
    }

    @Test
    public void deleteOrderTest() throws Exception {
        // Given

        //When & Then
        mockMvc.perform(delete("/v1/order/deleteOrder?orderId=2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    /*@Test
    public void createOrderTest() throws Exception {
        // Given
        Order order = new Order(10, "orderNameTest",true, LocalDate.of(2021,06,05), LocalDate.of(2021,06,06));
        OrderDto orderDto = new OrderDto(10, "orderNameTest",true, LocalDate.of(2021,06,05), LocalDate.of(2021,06,06));

        when(orderDbService.saveOrder(any(Order.class))).thenReturn(order);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(orderDto);

        //When & Then
        mockMvc
                .perform(post("/v1/order/createNewOrder")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }*/
}
