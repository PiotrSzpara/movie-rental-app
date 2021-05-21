package com.crud.movies.controller;

import com.crud.movies.domain.MovieDto;
import com.crud.movies.domain.UserDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    @RequestMapping(method = RequestMethod.GET, value = "getAllUsers")
    public List<UserDto> getAllUsers() {
        return new ArrayList();
    }

    @RequestMapping(method = RequestMethod.GET, value = "getUser")
    public UserDto getUser(@RequestParam int userId) {
        return new UserDto(1, "tit", "descr", "popp", "0988hhu0", true);
    }

    @RequestMapping(method = RequestMethod.POST, value = "createUser", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody UserDto userDto) {

    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteUser")
    public void deleteUser(@RequestParam int userId) {

    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateUser")
    public void updateUser(@RequestParam int userId) {

    }
}
