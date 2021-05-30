package com.crud.movies.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "GENRES")
public class Genre {

    private int genreId;
    private String genreType;
    private String genreDescription;
    private List<Movie> movies = new ArrayList<>();

    public Genre(int genreId, String genreType, String genreDescription) {
        this.genreId = genreId;
        this.genreType = genreType;
        this.genreDescription = genreDescription;
    }

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "GENRE_ID", unique = true)
    public int getGenreId() {
        return genreId;
    }


    @Column(name = "GENRE_TYPE", length = 45)
    public String getGenreType() {
        return genreType;
    }

    @Column(name = "GENRE_DESCRIPTION", length = 400)
    public String getGenreDescription() {
        return genreDescription;
    }

    @OneToMany(
            targetEntity = Movie.class,
            mappedBy = "genre",
            fetch = FetchType.LAZY)
    public List<Movie> getMovies() {
        return movies;
    }


}
