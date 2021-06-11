package com.crud.movies.facade;

import com.crud.movies.domain.Rent;
import com.crud.movies.domain.RentDto;
import com.crud.movies.mapper.RentMapper;
import com.crud.movies.service.RentDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Component
public class RentFacade {

    private final RentDbService rentDbService;
    private final RentMapper rentMapper;

    public List<RentDto> getAllRents() {
        List<Rent> rents = rentDbService.getAllRents();
        return rentMapper.mapToRentDtoList(rents);
    }

    public RentDto getRentById(@RequestParam int rentId) {
        Rent rent = rentDbService.getRentById(rentId);
        return rentMapper.mapToRentDto(rent);
    }

    public void createRent(@RequestBody RentDto rentDto) {
        Rent newRent = rentMapper.mapToRent(rentDto);
        rentDbService.saveRent(newRent);
    }

    public void deleteRent(@RequestParam int rentId) {
        rentDbService.deleteRentById(rentId);
    }

    public RentDto updateRent(@RequestBody RentDto rentDto) {
        Rent rent = rentMapper.mapToRent(rentDto);
        Rent updatedRent = rentDbService.saveRent(rent);
        return rentMapper.mapToRentDto(updatedRent);
    }
}
