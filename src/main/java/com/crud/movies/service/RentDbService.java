package com.crud.movies.service;


import com.crud.movies.domain.Rent;
import com.crud.movies.domain.RentDao;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class RentDbService {
    @Autowired
    private final RentDao rentDao;

    public List<Rent> getAllRents() {
        return rentDao.findAll();
    }

    public Rent getRent(final int rentId) {
        return rentDao.findById(rentId);
    }

    public Rent saveRent(Rent rent) {
        return rentDao.save(rent);
    }

    public void deleteRentById(int rentId) {
        rentDao.deleteById(rentId);
    }
}
