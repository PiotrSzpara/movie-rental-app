package com.crud.movies.facade;

import com.crud.movies.domain.User;
import com.crud.movies.domain.UserDto;
import com.crud.movies.mapper.UserMapper;
import com.crud.movies.service.UserDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
public class UserFacade {

    private final UserDbService userDbService;
    private final UserMapper userMapper;
    private final SearchingFacade searchingFacade;

    @GetMapping(value = "getAllUsers")
    public List<UserDto> getAllUsers() {
        List<User> users = userDbService.getAllUsers();
        return userMapper.mapToUserDtoList(users);
    }

    @GetMapping(value = "getUserById")
    public UserDto getUserById(@RequestParam int userId) {
        User user = userDbService.getUserById(userId);
        return userMapper.mapToUserDto(user);
    }

    @GetMapping(value = "getUserByName")
    public UserDto getUserByName(@RequestParam String userName) {
        User user = userDbService.getUserByNme(userName);
        return userMapper.mapToUserDto(user);
    }

    @GetMapping(value = "getUsersByNameFragment")
    public List<UserDto> getUsersByNameFragment(@RequestParam String nameFragment) throws SearchException {
        return userMapper.mapToUserDtoList(searchingFacade.usersWithName(nameFragment));
    }

    @PostMapping(value = "createUser", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody UserDto userDto) {
        User newUser = userMapper.mapToUser(userDto);
        userDbService.saveUser(newUser);
    }

    @DeleteMapping(value = "deleteUser")
    public void deleteUser(@RequestParam int userId) {
        userDbService.deleteUserById(userId);
    }

    @PutMapping(value = "updateUser")
    public UserDto updateUser(@RequestBody UserDto userDto) {
        User user = userMapper.mapToUser(userDto);
        User updatedUser = userDbService.saveUser(user);
        return userMapper.mapToUserDto(updatedUser);
    }

    @PutMapping(value = "updateUserPassword")
    public UserDto updateUserPassword(@RequestBody UserDto userDto, String newPassword) {
        User user = userMapper.mapToUser(userDto);
        user.setPassword(newPassword);
        User updatedUser = userDbService.saveUser(user);
        return userMapper.mapToUserDto(updatedUser);
    }

    @PostMapping(value = "createTokenUserKey")
    public void createUserTokenKey(@RequestBody UserDto userDto) {
        User user = userMapper.mapToUser(userDto);
        User userWithToken = userDbService.saveTokenUserKey(user);
        userDbService.saveUser(userWithToken);
    }
}
