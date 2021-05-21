package com.crud.movies.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface OrderDao extends CrudRepository<Order, Integer> {

        Order findById(int id);
}
