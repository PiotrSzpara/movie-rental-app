package com.crud.movies.domain;

import java.util.ArrayList;
import java.util.List;

public class Rent {

    private int rentId;
    private Order order;
    private User user;
    private List<Movie> movies = new ArrayList<>();

    public Rent(int rentId) {
        this.rentId = rentId;
    }
}
