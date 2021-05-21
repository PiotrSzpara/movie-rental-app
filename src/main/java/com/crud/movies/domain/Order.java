package com.crud.movies.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ORDERS")
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
