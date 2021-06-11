package com.crud.movies.service;

import com.crud.movies.domain.Rent;
import com.crud.movies.domain.User;
import com.crud.movies.repository.RentRepository;
import com.crud.movies.repository.UserRepository;
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
public class UserDbServiceTestSuite {

    @InjectMocks
    private UserDbService userDbService;

    @Mock
    private UserRepository userRepository;


    @Test
    public void getAllUsersTest() {
        //Given
        User user = new User(1, "userNameTest", "userEmailTest", "userPasswordTest", "userTokenKey", true);

        List<User> userList = new ArrayList<>();
        userList.add(user);

        when(userRepository.findAll()).thenReturn(userList);

        //When
        List<User> theList = userDbService.getAllUsers();

        //Then
        assertEquals(1, theList.size());
        assertNotNull(theList);
    }
    @Test
    public void getUserTest() {
        //Given
        User user = new User(1, "userNameTest", "userEmailTest", "userPasswordTest", "userTokenKey", true);

        List<User> userList = new ArrayList<>();
        userList.add(user);

        when(userRepository.findByUserId(1)).thenReturn(user);

        //When
        User theUser = userDbService.getUserById(1);

        //Then
        assertEquals(user, theUser);
    }

    @Test
    public void saveUserTest() {
        //Given
        User user = new User(1, "userNameTest", "userEmailTest", "userPasswordTest", "userTokenKey", true);

        when(userRepository.save(user)).thenReturn(user);

        //When
        User savedUser = userDbService.saveUser(user);

        //Then
        assertEquals(user.getUserId(), savedUser.getUserId());
        assertEquals(user.getUserName(), savedUser.getUserName());
        assertEquals(user.getUserEmail(), savedUser.getUserEmail());
        assertEquals(user.getPassword(), savedUser.getPassword());
        assertEquals(user.getUserTokenKey(), savedUser.getUserTokenKey());
        assertEquals(user.getRents(), savedUser.getRents());
    }

    @Test
    public void deleteUserTest() {
        //Given
        User user = new User(1, "userNameTest", "userEmailTest", "userPasswordTest", "userTokenKey", true);

        int id = user.getUserId();

        userDbService.deleteUserById(id);

        //When
        User userToDelete = userDbService.getUserById(id);
        //Then
        assertNull(userToDelete);
    }
}
