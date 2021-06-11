package com.crud.movies.service;


import com.crud.movies.domain.Rent;
import com.crud.movies.repository.RentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class RentDbService {

    private final RentRepository rentRepository;

    public List<Rent> getAllRents() {
        return rentRepository.findAll();
    }

    public Rent getRentById(final int rentId) {
        return rentRepository.findByRentId(rentId);
    }

    public Rent saveRent(Rent rent) {
        return rentRepository.save(rent);
    }

    public void deleteRentById(int rentId) {
        rentRepository.deleteById(rentId);
    }
}
