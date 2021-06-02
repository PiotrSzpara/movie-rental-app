package com.crud.movies.service;


import com.crud.movies.domain.User;
import com.crud.movies.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class UserDbService {


    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById (final int userId) {
        return userRepository.findByUserId(userId);
    }

    public User getUserByNme (final String userName) {
        return userRepository.findByUserName(userName);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUserById(int userId) {
        userRepository.deleteById(userId);
    }

    public User saveTokenUserKey(User user) {
        Random random = new Random();
        String userTokenKey = String.valueOf(random.nextInt(8999)+1000) ;
        user.setUserTokenKey(userTokenKey);
        return user;
    }
}
