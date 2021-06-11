package com.crud.movies.facade;

import com.crud.movies.domain.User;
import com.crud.movies.domain.UserDto;
import com.crud.movies.mapper.UserMapper;
import com.crud.movies.repository.UserRepository;
import com.crud.movies.service.UserDbService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Component
public class UserFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserFacade.class);

    private final UserDbService userDbService;
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public List<UserDto> getAllUsers() {
        List<User> users = userDbService.getAllUsers();
        return userMapper.mapToUserDtoList(users);
    }

    public UserDto getUserById(@RequestParam int userId) {
        User user = userDbService.getUserById(userId);
        return userMapper.mapToUserDto(user);
    }

    public UserDto getUserByName(@RequestParam String userName) {
        User user = userDbService.getUserByName(userName);
        return userMapper.mapToUserDto(user);
    }
    public UserDto getUserByEmail(@RequestParam String userEmail) {
        User user = userDbService.getUserByEmail(userEmail);
        return userMapper.mapToUserDto(user);
    }
    public List<UserDto> getUsersByNameFragment (@RequestParam String nameFragment) throws SearchException{
        if (nameFragment.length() == 0) {
            LOGGER.error(SearchException.ERR_NAME_FRAGMENT_IS_NULL);
            throw new SearchException(SearchException.ERR_NAME_FRAGMENT_IS_NULL);
        }

        LOGGER.info("SEARCHING FOR USERS WITH NAME CONTAINS: " + nameFragment);

        List<User>  listOfUsersFound = userRepository.usersWithName(nameFragment);
        if(listOfUsersFound.isEmpty()) {
            LOGGER.info("NO USERS FOUND WHERE NAME CONTAINS: " + nameFragment);
        }
        for(User user : listOfUsersFound) {
            LOGGER.info("USER THAT MATCH THE CRITERIA: " + user.getUserName());
        }
        LOGGER.info("SEARCHING PROCESS ENDED");

        return userMapper.mapToUserDtoList(listOfUsersFound);
    }

    public void createUser(@RequestBody UserDto userDto) {
        User newUser = userMapper.mapToUser(userDto);
        userDbService.saveUser(newUser);
    }

    public void deleteUser(@RequestParam int userId) {
        userDbService.deleteUserById(userId);
    }

    public UserDto updateUser(@RequestBody UserDto userDto) {
        User user = userMapper.mapToUser(userDto);
        User updatedUser = userDbService.saveUser(user);
        return userMapper.mapToUserDto(updatedUser);
    }

    public UserDto updateUserPassword(@RequestBody UserDto userDto, String newPassword) {
        User user = userMapper.mapToUser(userDto);
        user.setPassword(newPassword);
        User updatedUser = userDbService.saveUser(user);
        return userMapper.mapToUserDto(updatedUser);
    }

    public void createUserTokenKey(@RequestBody UserDto userDto) {
        User user = userMapper.mapToUser(userDto);
        User userWithToken = userDbService.saveTokenUserKey(user);
        userDbService.saveUser(userWithToken);
    }
}
