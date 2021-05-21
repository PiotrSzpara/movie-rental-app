package com.crud.movies.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private int orderId;
    private String orderName;
    private boolean isPaid;
    private Date orderDate;
    private Date orderDateEnd;
}
