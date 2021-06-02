package com.crud.movies.repository;


import com.crud.movies.domain.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer> {

        Genre findByGenreId(int genreId);
}
