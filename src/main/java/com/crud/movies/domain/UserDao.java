package com.crud.movies.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface UserDao extends CrudRepository<User, Integer> {

        User findById(int userId);

        User findByName(String userName);

        @Override
        User save(User user);

        @Override
        List<User> findAll();
}
