package com.crud.movies.mapper;

import com.crud.movies.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RentMapperTestSuite {

    @Autowired
    private RentMapper rentMapper;

    @Test
    public void testMapToRent() {
        //Given
        Rent rent = new Rent(1);
        RentDto rentDto = new RentDto(1);
        //When
        Rent mappedRent = rentMapper.mapToRent(rentDto);

        //Then
        assertEquals(rentDto.getRentId(),mappedRent.getRentId());
    }

    @Test
    public void testMapToMovieDto() {
        //Given
        Rent rent = new Rent(1);
        RentDto rentDto = new RentDto(1);
        //When
        RentDto mappedRent = rentMapper.mapToRentDto(rent);

        //Then
        assertEquals(rent.getRentId(),mappedRent.getRentId());
    }

    @Test
    public void testMapToMovieDtoList() {
        //Given
        Rent rent = new Rent(1);
        List<Rent> rentList = new ArrayList<>();
        rentList.add(rent);

        RentDto rentDto = new RentDto(1);
        List<RentDto> rentDtoList = new ArrayList<>();
        rentDtoList.add(rentDto);

        //When
        List<RentDto> mappedRentDtoList = rentMapper.mapToRentDtoList(rentList);

        //Then
        assertEquals(rentDtoList.size(),mappedRentDtoList.size());
        assertEquals(rentDtoList.get(0).getRentId(),mappedRentDtoList.get(0).getRentId());
    }
}
