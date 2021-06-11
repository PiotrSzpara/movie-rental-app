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
public class UserTestSuite {

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
    public void testUserRepositorySave() {
        //Given
        User user = new User();

        //When
        userRepository.deleteAll();
        userRepository.save(user);

        //Then
        List<User> users = userRepository.findAll();
        assertEquals(1, users.size());

        //CleanUp
        userRepository.deleteAll();
    }

    @Test
    public void testUserRepositoryDelete() {
        //Given
        User user = new User();

        //When
        userRepository.deleteAll();
        userRepository.save(user);
        userRepository.delete(user);

        //Then
        List<User> users = userRepository.findAll();
        assertEquals(0, users.size());

        //CleanUp
        userRepository.deleteAll();
    }

    @Test
    public void testCreateNewUser() {
        //Given
        User user = new User();

        //When
        userRepository.save(user);

        //Then
        int id = user.getUserId();
        User readUser = userRepository.findByUserId(id);
        assertEquals(readUser.getUserId(),id);

        //CleanUp
        userRepository.deleteById(id);
    }
}
