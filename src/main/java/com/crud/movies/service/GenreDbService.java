package com.crud.movies.service;

import com.crud.movies.domain.Genre;
import com.crud.movies.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreDbService {

    private final GenreRepository genreRepository;

    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    public Genre getGenre (final int genreId) {
        return genreRepository.findByGenreId(genreId);
    }

    public Genre saveGenre(Genre genre) {
        return genreRepository.save(genre);
    }

    public void deleteGenreById(int genreId) {
        genreRepository.deleteById(genreId);
    }
}
