package com.crud.movies.mapper;

import com.crud.movies.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTestSuite {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testMapToUser() {
        //Given
        User user = new User(1, "userNameTest", "userEmailTest", "userPasswordTest", "userTokenKey", true);
        UserDto userDto = new UserDto(1, "userNameTest", "userEmailTest", "userPasswordTest", "userTokenKey", true);

        //When
        User mappedUser = userMapper.mapToUser(userDto);

        //Then
        assertEquals(userDto.getUserId(),mappedUser.getUserId());
        assertEquals(userDto.getUserName(), mappedUser.getUserName());
        assertEquals(userDto.getUserEmail(), mappedUser.getUserEmail());
        assertEquals(userDto.getPassword(), mappedUser.getPassword());
        assertEquals(userDto.getUserTokenKey(), mappedUser.getUserTokenKey());
    }

    @Test
    public void testMapToMovieDto() {
        //Given
        User user = new User(1, "userNameTest", "userEmailTest", "userPasswordTest", "userTokenKey", true);
        UserDto userDto = new UserDto(1, "userNameTest", "userEmailTest", "userPasswordTest", "userTokenKey", true);

        //When
        UserDto mappedUser = userMapper.mapToUserDto(user);

        //Then
        assertEquals(user.getUserId(),mappedUser.getUserId());
        assertEquals(user.getUserName(), mappedUser.getUserName());
        assertEquals(user.getUserEmail(), mappedUser.getUserEmail());
        assertEquals(user.getPassword(), mappedUser.getPassword());
        assertEquals(user.getUserTokenKey(), mappedUser.getUserTokenKey());
    }

    @Test
    public void testMapToMovieDtoList() {
        //Given
        User user = new User(1, "userNameTest", "userEmailTest", "userPasswordTest", "userTokenKey", true);
        List<User> userList = new ArrayList<>();
        userList.add(user);

        UserDto userDto = new UserDto(1, "userNameTest", "userEmailTest", "userPasswordTest", "userTokenKey", true);
        List<UserDto> userDtoList = new ArrayList<>();
        userDtoList.add(userDto);

        //When
        List<UserDto> mappedUserDtoList = userMapper.mapToUserDtoList(userList);

        //Then
        assertEquals(userDtoList.size(),mappedUserDtoList.size());
        assertEquals(userDtoList.get(0).getUserId(),mappedUserDtoList.get(0).getUserId());
        assertEquals(userDtoList.get(0).getUserName(),mappedUserDtoList.get(0).getUserName());
        assertEquals(userDtoList.get(0).getUserEmail(),mappedUserDtoList.get(0).getUserEmail());
        assertEquals(userDtoList.get(0).getUserTokenKey(),mappedUserDtoList.get(0).getUserTokenKey());
        assertEquals(userDtoList.get(0).getPassword(),mappedUserDtoList.get(0).getPassword());
    }

}
