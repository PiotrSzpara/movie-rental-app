package com.crud.movies.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {

    private int orderId;
    private String orderName;
    private boolean isPaid;
    private Date orderDate;
    private Date orderDateEnd;
    private Rent rent;
    private List<Movie> movies = new ArrayList<>();

    public Order(int orderId, String orderName, boolean isPaid, Date orderDate, Date orderDateEnd) {
        this.orderId = orderId;
        this.orderName = orderName;
        this.isPaid = isPaid;
        this.orderDate = orderDate;
        this.orderDateEnd = orderDateEnd;

    }
}
