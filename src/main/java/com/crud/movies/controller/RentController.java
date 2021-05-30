package com.crud.movies.controller;

import com.crud.movies.domain.Rent;
import com.crud.movies.domain.RentDto;
import com.crud.movies.mapper.RentMapper;
import com.crud.movies.service.RentDbService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/rent")
public class RentController {

    private final RentDbService rentDbService;
    private final RentMapper rentMapper;

    public RentController(RentDbService rentDbService, RentMapper rentMapper) {
        this.rentDbService = rentDbService;
        this.rentMapper = rentMapper;
    }

    @RequestMapping(method = RequestMethod.GET, value = "getAllRents")
    public List<RentDto> getAllRents() {
        List<Rent> rents = rentDbService.getAllRents();
        return rentMapper.mapToRentDtoList(rents);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getRent")
    public RentDto getRent(@RequestParam int rentId) {
        Rent rent = rentDbService.getRent(rentId);
        return rentMapper.mapToRentDto(rent);
    }

    @RequestMapping(method = RequestMethod.POST, value = "createRent", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createRent(@RequestBody RentDto rentDto) {
        Rent newRent = rentMapper.mapToRent(rentDto);
        rentDbService.saveRent(newRent);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteRent")
    public void deleteRent(@RequestParam int rentId) {
        rentDbService.deleteRentById(rentId);

    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateRent")
    public RentDto updateRent(@RequestBody RentDto rentDto) {
        Rent rent = rentMapper.mapToRent(rentDto);
        Rent updatedRent = rentDbService.saveRent(rent);
        return rentMapper.mapToRentDto(updatedRent);
    }
}
