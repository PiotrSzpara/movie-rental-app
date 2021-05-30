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
@Table(name = "RENTS")
public class Rent {

    private int rentId;
    private Order order;
    private User user;
    private List<Movie> movies = new ArrayList<>();

    public Rent(int rentId) {
        this.rentId = rentId;
    }

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "RENT_ID", unique = true)
    public int getRentId() {
        return rentId;
    }

    @OneToOne(cascade = CascadeType.PERSIST)
    public Order getOrder() {
        return order;
    }

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH})
    public User getUser() {
        return user;
    }

    @ManyToMany(cascade = CascadeType.PERSIST, mappedBy = "rents")
    public List<Movie> getMovies() {
        return movies;
    }
}
