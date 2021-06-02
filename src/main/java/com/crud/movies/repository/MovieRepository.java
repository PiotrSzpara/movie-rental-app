package com.crud.movies.repository;

import com.crud.movies.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

    Movie findByMovieId(int movieId);

    Movie findByMovieTitle(String movieTitle);

    @Query(nativeQuery = true)
    List<Movie> moviesWithTitle(@Param("KEYWORD") String titleFragment);
}
