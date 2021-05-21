package com.crud.movies.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
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
}
