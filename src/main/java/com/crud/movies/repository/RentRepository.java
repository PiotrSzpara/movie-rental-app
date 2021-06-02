package com.crud.movies.repository;

import com.crud.movies.domain.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface RentRepository extends JpaRepository<Rent, Integer> {

    Rent findByRentId(int rentId);
}
