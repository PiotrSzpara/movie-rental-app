package com.crud.movies.repository;

import com.crud.movies.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

        Order findByOrderId(int orderId);

        Order findByOrderName(String orderName);
}
