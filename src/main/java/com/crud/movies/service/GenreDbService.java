package com.crud.movies.service;

import com.crud.movies.domain.Genre;
import com.crud.movies.domain.GenreDao;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreDbService {
    @Autowired
    private final GenreDao genreDao;

    public List<Genre> getAllGenres() {
        return genreDao.findAll();
    }

    public Genre getGenre (final int genreId) {
        return genreDao.findById(genreId);
    }

    public Genre saveGenre(Genre genre) {
        return genreDao.save(genre);
    }

    public void deleteGenreById(int genreId) {
        genreDao.deleteById(genreId);
    }
}
