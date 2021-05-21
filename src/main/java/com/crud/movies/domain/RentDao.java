package com.crud.movies.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface RentDao extends CrudRepository<Rent, Integer> {

    Rent findById(int id);
}
