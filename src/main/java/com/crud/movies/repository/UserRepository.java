package com.crud.movies.repository;

import com.crud.movies.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

        User findByUserId(int userId);

        User findByUserName(String userName);

        @Query(nativeQuery = true)
        List<User> usersWithName(@Param("KEYWORD") String nameFragment);
}
