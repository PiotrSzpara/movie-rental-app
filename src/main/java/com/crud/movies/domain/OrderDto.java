package com.crud.movies.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private int orderId;
    private String orderName;
    private boolean paid;
    private LocalDate orderDate;
    private LocalDate orderDateEnd;
}
