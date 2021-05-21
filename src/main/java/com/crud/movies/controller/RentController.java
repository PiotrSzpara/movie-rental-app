package com.crud.movies.controller;

import com.crud.movies.domain.MovieDto;
import com.crud.movies.domain.RentDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/rent")
public class RentController {

    @RequestMapping(method = RequestMethod.GET, value = "getAllRents")
    public List<RentDto> getAllRents() {
        return new ArrayList();
    }

    @RequestMapping(method = RequestMethod.GET, value = "getRent")
    public RentDto getRent(@RequestParam int rentId) {
        return new RentDto(1);
    }

    @RequestMapping(method = RequestMethod.POST, value = "createRent", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createRent(@RequestBody RentDto rentDto) {

    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteRent")
    public void deleteRent(@RequestParam int rentId) {

    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateRent")
    public void updateRent(@RequestParam int rentId) {

    }
}
