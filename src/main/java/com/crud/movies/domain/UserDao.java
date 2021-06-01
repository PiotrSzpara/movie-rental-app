package com.crud.movies.domain;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface UserDao extends CrudRepository<User, Integer> {

        User findByUserId(int userId);

        User findByUserName(String userName);

        @Override
        User save(User user);

        @Override
        List<User> findAll();

        @Query(nativeQuery = true)
        List<User> usersWithName(@Param("KEYWORD") String nameFragment);
}
