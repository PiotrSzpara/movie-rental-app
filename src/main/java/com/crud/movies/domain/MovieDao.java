package com.crud.movies.domain;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
@Repository
public interface MovieDao extends CrudRepository<Movie, Integer> {

    Movie findByMovieId(int movieId);

    Movie findByMovieTitle(String movieTitle);

    @Override
    Movie save(Movie movie);

    @Override
    List<Movie> findAll();

    @Query(nativeQuery = true)
    List<Movie> moviesWithTitle(@Param("KEYWORD") String titleFragment);
}
