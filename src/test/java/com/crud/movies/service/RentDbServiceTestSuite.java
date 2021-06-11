package com.crud.movies.service;

import com.crud.movies.domain.Rent;
import com.crud.movies.repository.RentRepository;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class RentDbServiceTestSuite {

    @InjectMocks
    private RentDbService rentDbService;

    @Mock
    private RentRepository rentRepository;


    @Test
    public void getAllRentsTest() {
        //Given
        Rent rent = new Rent(1);

        List<Rent> rentList = new ArrayList<>();
        rentList.add(rent);

        when(rentRepository.findAll()).thenReturn(rentList);

        //When
        List<Rent> theList = rentDbService.getAllRents();

        //Then
        assertEquals(1, theList.size());
        assertNotNull(theList);
    }
    @Test
    public void getRentTest() {
        //Given
        Rent rent = new Rent(1);

        List<Rent> rentList = new ArrayList<>();
        rentList.add(rent);

        when(rentRepository.findByRentId(1)).thenReturn(rent);

        //When
        Rent theRent = rentDbService.getRentById(1);

        //Then
        assertEquals(rent, theRent);
    }

    @Test
    public void saveRentTest() {
        //Given
        Rent rent = new Rent(1);

        when(rentRepository.save(rent)).thenReturn(rent);

        //When
        Rent savedRent = rentDbService.saveRent(rent);

        //Then
        assertEquals(rent.getRentId(), savedRent.getRentId());
        assertEquals(rent.getOrder(), savedRent.getOrder());
        assertEquals(rent.getUser(), savedRent.getUser());
        assertEquals(rent.getMovies(), savedRent.getMovies());
    }

    @Test
    public void deleteRentTest() {
        //Given
        Rent rent = new Rent(1);

        int id = rent.getRentId();

        rentDbService.deleteRentById(id);

        //When
        Rent rentToDelete = rentDbService.getRentById(id);
        //Then
        assertNull(rentToDelete);
    }
}
