package com.crud.movies.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface GenreDao extends CrudRepository<Genre, Integer> {

        Genre findById(int genreId);

        @Override
        Genre save(Genre genre);

        @Override
        List<Genre> findAll();
}
