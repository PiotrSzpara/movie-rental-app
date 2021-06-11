package com.crud.movies.controller;

import com.crud.movies.domain.*;
import com.crud.movies.facade.UserFacade;
import com.crud.movies.mapper.UserMapper;
import com.crud.movies.service.UserDbService;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitWebConfig
@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTestSuite {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserDbService userDbService;

    @MockBean
    private UserFacade userFacade;

    @MockBean
    private UserMapper userMapper;

    @Test
    public void getAllUsersEmptyTest() throws Exception {
        // Given
        List<UserDto> users = new ArrayList<>();
        when(userFacade.getAllUsers()).thenReturn(users);

        //When & Then
        mockMvc
                .perform(get("/v1/user/getAllUsers")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200)) // or isOk()
                .andExpect(jsonPath("$", Matchers.hasSize(0)));
    }

    @Test
    public void getAllUsersTest() throws Exception {
        // Given
        User user = new User(1, "userNameTest", "userEmailTest", "userPasswordTest", "userTokenKey", true);
        UserDto userDto = new UserDto(1, "userNameTest", "userEmailTest", "userPasswordTest", "userTokenKey", true);

        List<UserDto> users = new ArrayList<>();
        users.add(userDto);
        when(userFacade.getAllUsers()).thenReturn(users);

        //When & Then
        mockMvc
                .perform(get("/v1/user/getAllUsers")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200)) // or isOk()
                .andExpect(jsonPath("$", Matchers.hasSize(1)));
    }

    @Test
    public void getUserTest() throws Exception {
        // Given
        User user = new User(1, "userNameTest", "userEmailTest", "userPasswordTest", "userTokenKey", true);
        UserDto userDto = new UserDto(1, "userNameTest", "userEmailTest", "userPasswordTest", "userTokenKey", true);

        when(userFacade.getUserById(1)).thenReturn(userDto);
        when(userMapper.mapToUser(userDto)).thenReturn(user);

        //When & Then
        mockMvc
                .perform(get("/v1/user/getUserById?userId=1")
                        .param("userId", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userName", Matchers.is("userNameTest")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userEmail", Matchers.is("userEmailTest")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password", Matchers.is("userPasswordTest")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userTokenKey", Matchers.is("userTokenKey")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status", Matchers.is(true)))
        ;

    }
    @Test
    public void getUserByNameTest() throws Exception {
        // Given
        User user = new User(1, "userNameTest", "userEmailTest", "userPasswordTest", "userTokenKey", true);
        UserDto userDto = new UserDto(1, "userNameTest", "userEmailTest", "userPasswordTest", "userTokenKey", true);


        when(userFacade.getUserByName("userNameTest")).thenReturn(userDto);
        when(userMapper.mapToUser(userDto)).thenReturn(user);

        //When & Then
        mockMvc
                .perform(get("/v1/user/getUserByName")
                        .param("userName", "userNameTest")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userName", Matchers.is("userNameTest")))
        ;
    }

    @Test
    public void getUsersByNameFragmentTest() throws Exception {
        // Given
        User user = new User(1, "userNameTest", "userEmailTest", "userPasswordTest", "userTokenKey", true);
        UserDto userDto = new UserDto(1, "userNameTest", "userEmailTest", "userPasswordTest", "userTokenKey", true);
        List<UserDto> usersDto = new ArrayList<>();
        usersDto.add(userDto);

        List<UserDto> filteredUsersDto = usersDto
                .stream()
                .filter(m -> m.getUserName().contains("ame"))
                .collect(Collectors.toList());

        when(userFacade.getUsersByNameFragment("ame")).thenReturn(filteredUsersDto);

        //When & Then
        mockMvc
                .perform(get("/v1/user/getUsersByNameFragment/ame")
                        //.param("movieTitle", "tle")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
        ;
    }

    @Test
    public void getUserByEmailTest() throws Exception {
        // Given
        User user = new User(1, "userNameTest", "userEmailTest", "userPasswordTest", "userTokenKey", true);
        UserDto userDto = new UserDto(1, "userNameTest", "userEmailTest", "userPasswordTest", "userTokenKey", true);


        when(userFacade.getUserByEmail("userEmailTest")).thenReturn(userDto);
        when(userMapper.mapToUser(userDto)).thenReturn(user);

        //When & Then
        mockMvc
                .perform(get("/v1/user/getUserByEmail")
                        .param("userEmail", "userEmailTest")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userEmail", Matchers.is("userEmailTest")))
        ;
    }

    @Test
    public void deleteUserTest() throws Exception {
        // Given

        //When & Then
        mockMvc
                .perform(delete("/v1/user/deleteUser?userId=1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void createUserTest() throws Exception {
        // Given
        User user = new User(1, "userNameTest", "userEmailTest", "userPasswordTest", "userTokenKey", true);
        UserDto userDto = new UserDto(1, "userNameTest", "userEmailTest", "userPasswordTest", "userTokenKey", true);

        when(userDbService.saveUser(any(User.class))).thenReturn(user);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(userDto);

        //When & Then
        mockMvc
                .perform(post("/v1/user/createUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    /*@Test
    public void updateUserTest() throws Exception {
        // Given
        User user = new User(1, "userNameTest", "userEmailTest", "userPasswordTest", "userTokenKey", true);
        UserDto userDto = new UserDto(1, "userNameTest", "userEmailTest", "userPasswordTest", "userTokenKey", true);

        UserDto userDto2 = new UserDto(2, "updatedUserNameTest", "updatedUserEmailTest", "updatedUserPasswordTest", "userTokenKey", true);

        //when(userFacade.updateUser(userDto)).thenReturn(userDto2);
        when(userDbService.saveUser(user)).thenReturn(new User(2, "updatedUserNameTest", "updatedUserEmailTest", "updatedUserPasswordTest", "userTokenKey", true));
        when(userMapper.mapToUserDto(any())).thenReturn(userDto2);
        when(userMapper.mapToUser(any())).thenReturn(user);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(userDto);

        //When & Then
        mockMvc
                .perform(put("/v1/user/updateUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userName", Matchers.is("updatedUserNameTest")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userEmail", Matchers.is("updatedUserEmailTest")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password", Matchers.is("updatedUserPasswordTest")))
        ;
    }*/
}

