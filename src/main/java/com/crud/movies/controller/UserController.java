package com.crud.movies.controller;

import com.crud.movies.domain.User;
import com.crud.movies.domain.UserDto;
import com.crud.movies.facade.SearchingFacade;
import com.crud.movies.facade.SearchException;
import com.crud.movies.mapper.UserMapper;
import com.crud.movies.service.UserDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    private final UserDbService userDbService;
    private final UserMapper userMapper;
    private final SearchingFacade searchingFacade;

    @Autowired
    public UserController(UserDbService userDbService, UserMapper userMapper, SearchingFacade searchingFacade) {
        this.userDbService = userDbService;
        this.userMapper = userMapper;
        this.searchingFacade = searchingFacade;
    }

    @RequestMapping(method = RequestMethod.GET, value = "getAllUsers")
    public List<UserDto> getAllUsers() {
        List<User> users = userDbService.getAllUsers();
        return userMapper.mapToUserDtoList(users);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getUserById")
    public UserDto getUserById(@RequestParam int userId) {
        User user = userDbService.getUserById(userId);
        return userMapper.mapToUserDto(user);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getUserByName")
    public UserDto getUserByName(@RequestParam String userName) {
        User user = userDbService.getUserByNme(userName);
        return userMapper.mapToUserDto(user);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getUsersByNameFragment")
    public List<UserDto> getUsersByNameFragment(@RequestParam String nameFragment) throws SearchException {
        return userMapper.mapToUserDtoList(searchingFacade.usersWithName(nameFragment));
    }

    @RequestMapping(method = RequestMethod.POST, value = "createUser", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody UserDto userDto) {
        User newUser = userMapper.mapToUser(userDto);
        userDbService.saveUser(newUser);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteUser")
    public void deleteUser(@RequestParam int userId) {
        userDbService.deleteUserById(userId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateUser")
    public UserDto updateUser(@RequestBody UserDto userDto) {
        User user = userMapper.mapToUser(userDto);
        User updatedUser = userDbService.saveUser(user);
        return userMapper.mapToUserDto(updatedUser);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateUserPassword")
    public UserDto updateUserPassword(@RequestBody UserDto userDto, String newPassword) {
        User user = userMapper.mapToUser(userDto);
        user.setPassword(newPassword);
        User updatedUser = userDbService.saveUser(user);
        return userMapper.mapToUserDto(updatedUser);
    }

    @RequestMapping(method = RequestMethod.POST, value = "createTokenUserKey")
    public void createUserTokenKey(@RequestBody UserDto userDto) {
        User user = userMapper.mapToUser(userDto);
        User userWithToken = userDbService.saveTokenUserKey(user);
        userDbService.saveUser(userWithToken);
    }
}
