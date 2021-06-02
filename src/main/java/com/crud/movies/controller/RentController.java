package com.crud.movies.controller;

import com.crud.movies.domain.RentDto;
import com.crud.movies.facade.RentFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/rent")
@RequiredArgsConstructor
public class RentController {

    private RentFacade rentFacade;

    @GetMapping(value = "getAllRents")
    public List<RentDto> getAllRents() {
        return rentFacade.getAllRents();
    }

    @GetMapping(value = "getRent")
    public RentDto getRent(@RequestParam int rentId) {
        return rentFacade.getRent(rentId);
    }

    @PostMapping(value = "createRent", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createRent(@RequestBody RentDto rentDto) {
        rentFacade.createRent(rentDto);
    }

    @DeleteMapping(value = "deleteRent")
    public void deleteRent(@RequestParam int rentId) {
        rentFacade.deleteRent(rentId);
    }

    @PutMapping(value = "updateRent")
    public RentDto updateRent(@RequestBody RentDto rentDto) {
        return rentFacade.updateRent(rentDto);
    }
}
