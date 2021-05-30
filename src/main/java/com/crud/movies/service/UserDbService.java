package com.crud.movies.service;


import com.crud.movies.domain.User;
import com.crud.movies.domain.UserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class UserDbService {

    @Autowired
    private final UserDao userDao;

    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    public User getUserById (final int userId) {
        return userDao.findById(userId);
    }

    public User getUserByNme (final String userName) {
        return userDao.findByName(userName);
    }

    public User saveUser(User user) {
        return userDao.save(user);
    }

    public void deleteUserById(int userId) {
        userDao.deleteById(userId);
    }

    public User saveTokenUserKey(User user) {
        Random random = new Random();
        String userTokenKey = String.valueOf(random.nextInt(8999)+1000) ;
        user.setUserTokenKey(userTokenKey);
        return user;
    }
}
