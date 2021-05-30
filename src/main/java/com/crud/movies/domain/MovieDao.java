package com.crud.movies.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface MovieDao extends CrudRepository<Movie, Integer> {

    Movie findById(int movieId);

    Movie findByTitle(String movieTitle);

    @Override
    Movie save(Movie movie);

    @Override
    List<Movie> findAll();
}
