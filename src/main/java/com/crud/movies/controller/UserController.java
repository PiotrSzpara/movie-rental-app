package com.crud.movies.controller;

import com.crud.movies.domain.UserDto;
import com.crud.movies.facade.SearchException;
import com.crud.movies.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
public class UserController {

    private UserFacade userFacade;

    @GetMapping(value = "getAllUsers")
    public List<UserDto> getAllUsers() {
        return userFacade.getAllUsers();
    }

    @GetMapping(value = "getUserById")
    public UserDto getUserById(@RequestParam int userId) {
        return userFacade.getUserById(userId);
    }

    @GetMapping(value = "getUserByName")
    public UserDto getUserByName(@RequestParam String userName) {
        return userFacade.getUserByName(userName);
    }

    @GetMapping(value = "getUsersByNameFragment")
    public List<UserDto> getUsersByNameFragment(@RequestParam String nameFragment) throws SearchException {
        return userFacade.getUsersByNameFragment(nameFragment);
    }

    @PostMapping(value = "createUser", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody UserDto userDto) {
        userFacade.createUser(userDto);
    }

    @DeleteMapping(value = "deleteUser")
    public void deleteUser(@RequestParam int userId) {
        userFacade.deleteUser(userId);
    }

    @PutMapping(value = "updateUser")
    public UserDto updateUser(@RequestBody UserDto userDto) {
        return userFacade.updateUser(userDto);
    }

    @PutMapping(value = "updateUserPassword")
    public UserDto updateUserPassword(@RequestBody UserDto userDto, String newPassword) {
        return userFacade.updateUserPassword(userDto, newPassword);
    }

    @PostMapping(value = "createTokenUserKey")
    public void createUserTokenKey(@RequestBody UserDto userDto) {
        userFacade.createUserTokenKey(userDto);
    }
}
