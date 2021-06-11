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
@CrossOrigin("*")
public class UserController {

    private final UserFacade userFacade;

    @GetMapping("getAllUsers")
    public List<UserDto> getAllUsers() {
        return userFacade.getAllUsers();
    }

    @GetMapping("getUserById")
    public UserDto getUserById(@RequestParam int userId) {
        return userFacade.getUserById(userId);
    }

    @GetMapping("getUserByName")
    public UserDto getUserByName(@RequestParam String userName) {
        return userFacade.getUserByName(userName);
    }

    @GetMapping("getUsersByNameFragment/{nameFragment}")
    public List<UserDto> getUsersByNameFragment(@PathVariable String nameFragment) throws SearchException {
        return userFacade.getUsersByNameFragment(nameFragment);
    }

    @GetMapping("getUserByEmail")
    public UserDto getUserByEmail(@RequestParam String userEmail) {
        return userFacade.getUserByEmail(userEmail);
    }


    @PostMapping(value = "createUser", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody UserDto userDto) {
        userFacade.createUser(userDto);
    }

    @DeleteMapping("deleteUser")
    public void deleteUser(@RequestParam int userId) {
        userFacade.deleteUser(userId);
    }

    @PutMapping("updateUser")
    public UserDto updateUser(@RequestBody UserDto userDto) {
        return userFacade.updateUser(userDto);
    }

    @PutMapping("updateUserPassword")
    public UserDto updateUserPassword(@RequestBody UserDto userDto, String newPassword) {
        return userFacade.updateUserPassword(userDto, newPassword);
    }

    @PostMapping("createTokenUserKey")
    public void createUserTokenKey(@RequestBody UserDto userDto) {
        userFacade.createUserTokenKey(userDto);
    }
}
