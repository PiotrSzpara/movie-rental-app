package com.crud.movies.mapper;

import com.crud.movies.domain.User;
import com.crud.movies.domain.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserMapper {

    public User mapToUser(final UserDto userDto) {
        return new User(
                userDto.getUserId(),
                userDto.getUserName(),
                userDto.getUserEmail(),
                userDto.getPassword(),
                userDto.getUserTokenKey(),
                userDto.isStatus()
        );
    }

    public UserDto mapToUserDto(final User user) {
        return new UserDto(
                user.getUserId(),
                user.getUserName(),
                user.getUserEmail(),
                user.getPassword(),
                user.getUserTokenKey(),
                user.isStatus()
        );
    }

    public List<UserDto> mapToUserDtoList(final List<User> users) {
        return users.stream()
                .map(this::mapToUserDto)
                .collect(Collectors.toList());
    }
}
