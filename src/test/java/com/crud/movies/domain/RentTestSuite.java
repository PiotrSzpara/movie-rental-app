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
public class RentTestSuite {

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
    public void testRentRepositorySave() {
        //Given
        Rent rent = new Rent();

        //When
        rentRepository.deleteAll();
        rentRepository.save(rent);

        //Then
        List<Rent> rents = rentRepository.findAll();
        assertEquals(1, rents.size());

        //CleanUp
        rentRepository.deleteAll();
    }

    @Test
    public void testRentRepositoryDelete() {
        //Given
        Rent rent = new Rent();

        //When
        rentRepository.deleteAll();
        rentRepository.save(rent);
        rentRepository.delete(rent);

        //Then
        List<Rent> rents = rentRepository.findAll();
        assertEquals(0, rents.size());

        //CleanUp
        rentRepository.deleteAll();
    }

    @Test
    public void testCreateNewRent() {
        //Given
        Rent rent = new Rent();

        //When
        rentRepository.save(rent);

        //Then
        int id = rent.getRentId();
        Rent readRent = rentRepository.findByRentId(id);
        assertEquals(readRent.getRentId(),id);

        //CleanUp
        rentRepository.deleteById(id);
    }
}
