package com.crud.movies.facade;

import com.crud.movies.domain.Rent;
import com.crud.movies.domain.RentDto;
import com.crud.movies.mapper.RentMapper;
import com.crud.movies.service.RentDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
public class RentFacade {

    private final RentDbService rentDbService;
    private final RentMapper rentMapper;

    @GetMapping(value = "getAllRents")
    public List<RentDto> getAllRents() {
        List<Rent> rents = rentDbService.getAllRents();
        return rentMapper.mapToRentDtoList(rents);
    }

    @GetMapping(value = "getRent")
    public RentDto getRent(@RequestParam int rentId) {
        Rent rent = rentDbService.getRent(rentId);
        return rentMapper.mapToRentDto(rent);
    }

    @PostMapping(value = "createRent", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createRent(@RequestBody RentDto rentDto) {
        Rent newRent = rentMapper.mapToRent(rentDto);
        rentDbService.saveRent(newRent);
    }

    @DeleteMapping(value = "deleteRent")
    public void deleteRent(@RequestParam int rentId) {
        rentDbService.deleteRentById(rentId);
    }

    @PutMapping(value = "updateRent")
    public RentDto updateRent(@RequestBody RentDto rentDto) {
        Rent rent = rentMapper.mapToRent(rentDto);
        Rent updatedRent = rentDbService.saveRent(rent);
        return rentMapper.mapToRentDto(updatedRent);
    }
}
