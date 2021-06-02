package com.crud.movies.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
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
    private LocalDate orderDate;
    private LocalDate orderDateEnd;
    private Rent rent;
    private List<Movie> movies = new ArrayList<>();

    public Order(int orderId, String orderName, boolean isPaid, LocalDate orderDate, LocalDate orderDateEnd) {
        this.orderId = orderId;
        this.orderName = orderName;
        this.isPaid = isPaid;
        this.orderDate = orderDate;
        this.orderDateEnd = orderDateEnd;

    }


    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "ORDER_ID", unique = true)
    public int getOrderId() {
        return orderId;
    }

    @Column(name = "ORDER_NAME")
    public String getOrderName() {
        return orderName;
    }

    @Column(name = "IS_PAID")
    public boolean isPaid() {
        return isPaid;
    }

    @Column(name = "ORDER_DATE")
    public LocalDate getOrderDate() {
        return orderDate;
    }

    @Column(name = "ORDER_DATE_END")
    public LocalDate getOrderDateEnd() {
        return orderDateEnd;
    }

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "RENT_ID")
    public Rent getRent() {
        return rent;
    }

    @ManyToMany(cascade = CascadeType.PERSIST, mappedBy = "orders")
    public List<Movie> getMovies() {
        return movies;
    }
}
