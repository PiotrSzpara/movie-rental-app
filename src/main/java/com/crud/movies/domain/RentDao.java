package com.crud.movies.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface RentDao extends CrudRepository<Rent, Integer> {

    Rent findById(int rentId);

    @Override
    Rent save(Rent rent);

    @Override
    List<Rent> findAll();
}
