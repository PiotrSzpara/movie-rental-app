package com.crud.movies.controller;


import com.crud.movies.domain.*;
import com.crud.movies.facade.RentFacade;
import com.crud.movies.mapper.RentMapper;
import com.crud.movies.service.RentDbService;
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

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitWebConfig
@RunWith(SpringRunner.class)
@WebMvcTest(RentController.class)
public class RentControllerTestSuite {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RentDbService rentDbService;

    @MockBean
    private RentFacade rentFacade;

    @MockBean
    private RentMapper rentMapper;

    @Test
    public void getAllRentsEmptyTest() throws Exception {
        // Given
        List<RentDto> rents = new ArrayList<>();
        when(rentFacade.getAllRents()).thenReturn(rents);

        //When & Then
        mockMvc
                .perform(get("/v1/rent/getAllRents")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200)) // or isOk()
                .andExpect(jsonPath("$", Matchers.hasSize(0)));
    }

    @Test
    public void getAllRentsTest() throws Exception {
        // Given
        Rent rent = new Rent(1);
        RentDto rentDto = new RentDto(1);

        List<RentDto> rents = new ArrayList<>();
        rents.add(rentDto);
        when(rentFacade.getAllRents()).thenReturn(rents);

        //When & Then
        mockMvc
                .perform(get("/v1/rent/getAllRents")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200)) // or isOk()
                .andExpect(jsonPath("$", Matchers.hasSize(1)));
    }

    @Test
    public void getRentTest() throws Exception {
        // Given
        Rent rent = new Rent(1);
        RentDto rentDto = new RentDto(1);

        when(rentFacade.getRentById(1)).thenReturn(rentDto);
        when(rentMapper.mapToRent(rentDto)).thenReturn(rent);

        //When & Then
        mockMvc
                .perform(get("/v1/rent/getRentById?rentId=1")
                        .param("rentId", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rentId", Matchers.is(1)))
          ;

    }

    @Test
    public void deleteRentTest() throws Exception {
        // Given

        //When & Then
        mockMvc.perform(delete("/v1/rent/deleteRent?rentId=1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    /*@Test
    public void createRentTest() throws Exception {
        // Given
        Rent rent = new Rent(1);
        RentDto rentDto = new RentDto(1);

        when(rentDbService.saveRent(any(Rent.class))).thenReturn(rent);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(rentDto);

        //When & Then
        mockMvc.perform(post("/v1/rent/createRent")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }*/
}
